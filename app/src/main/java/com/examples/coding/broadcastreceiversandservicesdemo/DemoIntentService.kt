package com.examples.coding.broadcastreceiversandservicesdemo

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

const val ACTION_FIND_VALUE = "com.examples.coding.broadcastreceiversandservicesdemo.action.FOO"
const val BASE_VALUE =
    "com.examples.coding.broadcastreceiversandservicesdemo.extra.PARAM1"
 const val EXPONENT_VALUE =
    "com.examples.coding.broadcastreceiversandservicesdemo.extra.PARAM2"

class DemoIntentService : IntentService("DemoIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FIND_VALUE -> {
                val param1 = intent.getStringExtra(BASE_VALUE)
                val param2 = intent.getStringExtra(EXPONENT_VALUE)
                findPowersValue(param1, param2)
            }
        }
    }

    private fun findPowersValue(param1: String, param2: String) {
        val base = param1.toDouble()
        val exponent = param2.toInt()
        var result = 1.00
        for(i in 1..exponent) {
            result *= base
        }
        Log.d("TAG", "Result = $result")
    }

    companion object {
        @JvmStatic
        fun startActionFindValues(context: Context, param1: String, param2: String) {
            val intent = Intent(context, DemoIntentService::class.java).apply {
                action = ACTION_FIND_VALUE
                putExtra(BASE_VALUE, param1)
                putExtra(EXPONENT_VALUE, param2)
            }
            context.startService(intent)
        }
    }
}
