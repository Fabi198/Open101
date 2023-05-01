@file:Suppress("DEPRECATION")

package com.example.open101.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log

class MyService: Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i("Service", "Iniciado, podriamos lanzar una noti")

        Counter().execute()

        return START_NOT_STICKY
    }


    @SuppressLint("StaticFieldLeak")
    inner class Counter: AsyncTask<Unit, Unit, Unit>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Unit?) {
            for (i in 0..50000) {
                Log.i("Service", i.toString())
            }
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Log.i("Service", "Se finalizo el servicio")
            stopSelf()
        }

    }
}