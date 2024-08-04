package com.daleguan.walletapp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.EthereumState
import javax.inject.Inject

class Wallet_mainpage @Inject constructor(
    private val ethereum: Ethereum
    ): ViewModel() {
    val ethereumState = MediatorLiveData<EthereumState>().apply {
        addSource(ethereum.ethereumState) { newEthereumState ->
            value = newEthereumState
        }
    }


    // wrapp function call other RPC-read only methods
    fun sendRequest(request: EthereumRequest, callback: ((Any?) -> Unit)?) {
        ethereum.sendRequest(request, callback)
    }



}

