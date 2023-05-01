package com.example.open101.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyBoundService: Service() {

    private val binder = MyBinder()
    private val numberGenerator = java.util.Random()

    fun getRandomNumber() = numberGenerator.nextInt(500)


    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class MyBinder(): Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }
}