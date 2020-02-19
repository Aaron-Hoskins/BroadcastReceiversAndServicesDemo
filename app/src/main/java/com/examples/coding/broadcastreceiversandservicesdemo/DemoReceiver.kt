package com.examples.coding.broadcastreceiversandservicesdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DemoReceiver(val receiverCallback: ReceiverCallback) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val messageReceived = intent.getStringExtra("KEY")
        Log.d("TAG", "Message Received: $messageReceived")
        receiverCallback.passInfoToUI(messageReceived)

    }
}

interface ReceiverCallback {
    fun passInfoToUI(passedValue : String)
}
