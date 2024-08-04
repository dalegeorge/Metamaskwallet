package com.daleguan.walletapp

import android.app.DownloadManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import io.metamask.androidsdk.EthereumState
import io.metamask.androidsdk.Result

import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.metamask.androidsdk.DappMetadata

import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumMethod
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.SDKOptions
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


import java.net.URI
import java.net.URL



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

    val infuraAPIKey = ""

    val myOpenseaApi = ""

    var ethereum = Ethereum(context = context, dappMetadata = dappMetadata, SDKOptions(infuraAPIKey))

    val address  = "0x3cAd439d7AD8c071Ae63A60f74b100e0fDCe1cAD"

    val TAG = "metamask wallet"

    val wType = "ERC1155"

    var isAssetEmpty = false

    var aResult = "Placeholder for asset"

    val assetmsg = "Placeholder for asset "
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

            Text(text = aResult)

            // Sign message button
            DappButton(buttonText = stringResource(R.string.sign)) {
                onSignMessage()
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                            Log.d(TAG,"connection error: $result")

                        }
                        is Result.Success.Item -> {
                            Log.d(TAG, "connection result: $result" )
                        }
                        else -> {
                            Log.d(TAG,"Notiong happened and there is no connection")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            DappButton(buttonText = "Connect") {
                ethereum.connect(){
                        result ->
                    when(result) {
                        is Result.Error -> {
                            Log.d(TAG,"connection error: $result")

                        }
                        is Result.Success.Item -> {
                            Log.d(TAG, "Connection result: $result" )

                        }
                        else -> {
                            Log.d(TAG,"Notiong happened and there is no connection")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            DappButton(buttonText = stringResource(R.string.gettokenid)) {
                get(myOpenseaApi);
            }

            Spacer(modifier = Modifier.height(12.dp))


            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


fun get(myOpenseaApi:String) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://api.opensea.io/api/v2/chain/matic/contract/0x35CCb478bd5d71832C007a73C7f2c0925390Db95/nfts/1245")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("x-api-key", myOpenseaApi)
        .build()

    val response = client.newCall(request).execute()

    val responseBody = response.body!!.string()

    //Response
    println("Response Body: " + responseBody)

    //we could use jackson if we got a JSON
//    val mapperAll = ObjectMapper()
//    val objData = mapperAll.readTree(responseBody)
//
//    objData.get("data").forEachIndexed { index, jsonNode ->
//        println("$index $jsonNode")
//    }
}
