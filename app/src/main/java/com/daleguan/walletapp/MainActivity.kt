package com.daleguan.walletapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daleguan.walletapp.ui.theme.WalletAppTheme
import io.metamask.androidsdk.EthereumState

class MainActivity : ComponentActivity() {

    val ethereumviewmodel:Wallet_mainpage by viewModels()

    val ethereumState: EthereumState = EthereumState("","","0x3cAd439d7AD8c071Ae63A60f74b100e0fDCe1cAD")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    Greeting("Android")
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WalletAppTheme {
        Greeting("Android")
    }
}