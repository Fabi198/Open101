package com.example.open101.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MySecondBoundService: Service() {

    private val binder = MySecondBinder()

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class MySecondBinder: Binder() {
        fun getService(): MySecondBoundService = this@MySecondBoundService
    }
}