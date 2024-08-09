package com.daleguan.walletapp

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.daleguan.walletapp.ui.theme.WalletAppTheme
import io.metamask.androidsdk.EthereumState


class MainActivity : ComponentActivity() {

    val ethereumviewmodel:Wallet_mainpage by viewModels()

    val ethereumState: EthereumState = EthereumState("","","")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        setContent {
            WalletAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                   DappActionsScreen(
                       context = applicationContext,
                       ethereumState = ethereumState,
                       onSignMessage = { /*TODO*/ },
                       onChainedSign = { /*TODO*/ },
                       onSendTransaction = { /*TODO*/ },
                       onSwitchChain = { /*TODO*/ }) {

                   }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
