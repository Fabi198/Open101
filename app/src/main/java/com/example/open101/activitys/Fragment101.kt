@file:Suppress("SameParameterValue")

package com.example.open101.activitys

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.open101.R
import com.example.open101.databinding.ActivityFragment101Binding
import com.example.open101.fragments.ButtonFragment
import com.example.open101.fragments.FragmentListener
import com.example.open101.fragments.TextFragment

class Fragment101 : AppCompatActivity(), FragmentListener {

    private lateinit var binding: ActivityFragment101Binding
    private val idChannel = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragment101Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(binding.buttonFragmentContainer.id, ButtonFragment(), "ButtonFragment").commit()
    }

    private fun createChannel(name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(idChannel, name, importance)
            mChannel.description = description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun createCustomNoti(): Notification {
        return NotificationCompat.Builder(this, idChannel)
            .setSmallIcon(R.drawable.baseline_mail_outline_24)
            .setContentTitle("Fragments")
            .setContentText("Se ha lanzado el segundo fragment")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
    }

    @SuppressLint("MissingPermission")
    override fun addBookResponse() {
        supportFragmentManager.beginTransaction().add(R.id.textFragmentContainer, TextFragment()).commit()
        createChannel("Default", "Notificaciones")
        val notification = createCustomNoti()
        val notiManager = NotificationManagerCompat.from(this)
        notiManager.notify(1, notification)
    }

    override fun editBookResponse() {

    }

    override fun deleteBookResponse() {

    }

    override fun onBackTerms() {

    }


}