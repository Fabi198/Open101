@file:Suppress("DEPRECATION")

package com.example.open101.services

import android.app.IntentService
import android.content.Intent
import android.util.Log

@Suppress("DEPRECATION")
class MyIntentService: IntentService("MyIntentService") {

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        Log.i("Service", "Este servicio se inicio correctamente")

        for (i in 0..50000) {
            Log.i("Service", i.toString())
        }

        stopSelf()
    }

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        super.onDestroy()
        Log.i("Service", "En este punto el servicio finalizo")
    }
}