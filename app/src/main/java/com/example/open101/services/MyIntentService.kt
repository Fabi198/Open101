package com.example.open101.services

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService: IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        Log.i("Service", "Este servicio se inicio correctamente")

        for (i in 0..50000) {
            Log.i("Service", i.toString())
        }

        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Service", "En este punto el servicio finalizo")
    }
}