package com.daleguan.walletapp

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import io.metamask.androidsdk.EthereumState
import io.metamask.androidsdk.Result
import kotlinx.coroutines.launch

import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.RememberObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.Heading
import androidx.compose.ui.unit.dp
import io.metamask.androidsdk.DappMetadata

import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumMethod
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.Logger
import io.metamask.androidsdk.SDKOptions

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun DappActionsScreen(
    context: Context,
    ethereumState: EthereumState,
    onSignMessage: () -> Unit,
    onChainedSign: () -> Unit,
    onSendTransaction: () -> Unit,
    onSwitchChain: () -> Unit,
    onReadOnlyCalls: () -> Unit
) {
    val connected = ethereumState.selectedAddress.isNotEmpty()

    val dappMetadata = DappMetadata("Droid Dapp", "https://droiddapp.com")

    val infuraAPIKey = "e9aa1db813c24bb2a8ad976b838248a8"

    var ethereum = Ethereum(context = context, dappMetadata = dappMetadata, SDKOptions(infuraAPIKey))

    val address  = "0x3cAd439d7AD8c071Ae63A60f74b100e0fDCe1cAD"

    val TAG = "metamask wallet"
    Surface {
//        AppTopBar(navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


//            DappLabel(
//                heading = "Account:",
//                text = ethereumState.selectedAddress,
//                color = Color.Unspecified,
//                modifier = Modifier.padding(bottom = 36.dp)
//            )
//
//            DappLabel(
//                heading = "ChainId:",
//                text = ethereumState.chainId,
//                color = Color.Unspecified,
//                modifier = Modifier.padding(bottom = 36.dp)
//            )
            Text(text = "ChainId")

            Spacer(modifier = Modifier.weight(1f))

            Text(text = ethereumState.chainId)

            Spacer(modifier = Modifier.weight(1f))

            // Sign message button
            DappButton(buttonText = stringResource(R.string.sign)) {
                onSignMessage()
            }

            Spacer(modifier = Modifier.height(12.dp))

            DappButton(buttonText = stringResource(R.string.getbalance)){
                val params: List<String> = listOf(
                    address,
                    "latest"
                )

                val getBalace = EthereumRequest(
                    method = EthereumMethod.ETH_GET_BALANCE.value,
                    params = params
                )
                ethereum.connectWith(getBalace) {
                        result ->
                    when (result) {
                        is Result.Error -> {
                            Log.e(TAG,"connection error: $result")
                        }
                        is Result.Success.Item -> {
                            Log.e(TAG, "connection result: $result" )
                        }
                        else -> {
                            Log.e(TAG,"Notiong happened and there is no connection")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            DappButton(buttonText = stringResource(R.string.connect)) {

            }

            Spacer(modifier = Modifier.height(12.dp))


            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

