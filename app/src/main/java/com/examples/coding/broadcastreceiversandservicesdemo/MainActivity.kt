package com.examples.coding.broadcastreceiversandservicesdemo

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ReceiverCallback {
    val demoReceiver = DemoReceiver(this)
    val intentFilter = IntentFilter()
    val localDemoReceiver = DemoReceiver(this)
    lateinit var intentService : DemoIntentService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentFilter.addAction("com.examples.coding.broadcastreceiversandservicesdemo.demoreceiver")
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(demoReceiver, intentFilter)
        LocalBroadcastManager.getInstance(this).registerReceiver(localDemoReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(demoReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localDemoReceiver)
    }

    fun onClick(view: View) {
        when(view.id) {
            R.id.btnSendRegualarBroadcast -> {
                val enteredString = etStringToBroadcast.text.toString()
                val intent = Intent("com.examples.coding.broadcastreceiversandservicesdemo.demoreceiver")
                intent.putExtra("KEY", enteredString)
                sendBroadcast(intent)
            }
            R.id.btnSendLocalBroadcast -> {
                val enteredString = etStringToBroadcast.text.toString()
                val intent = Intent("com.examples.coding.broadcastreceiversandservicesdemo.demoreceiver")
                intent.putExtra("KEY", enteredString)
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            }
            R.id.btnSendPermissionBroadcast -> {
                val enteredString = etStringToBroadcast.text.toString()
                val intent = Intent("com.examples.coding.broadcastreceiversandservicesdemo.demoreceiver")
                intent.putExtra("KEY", enteredString)
                sendBroadcast(intent, "my.receivers.permission")
            }
            R.id.btnCalculateValue -> {
                val base = etBase.text.toString()
                val power = etPower.text.toString()

                DemoIntentService.startActionFindValues(this, base, power)

            }
        }
    }

    override fun passInfoToUI(passedValue: String) {
        tvDisplay.text = passedValue
    }
}
