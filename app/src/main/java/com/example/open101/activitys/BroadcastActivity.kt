package com.example.open101.activitys

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.open101.broadcast.AirplaneModeReceiver
import com.example.open101.databinding.ActivityBroadcastBinding

class BroadcastActivity : AppCompatActivity() {

    /*
     NO OLVIDAR DECLARAR EN EL MANIFEST:

     <receiver
            android:name="*************"
            android:exported="true">
            <intent-filter>
                <action android:name="***************" />
            </intent-filter>
        </receiver>

     */


    private lateinit var binding: ActivityBroadcastBinding
    val intentFilter = "El usuario hizo touch en el boton del main activity"
    private val airplaneBR = AirplaneModeReceiver()
    private val myLocalReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == intentFilter) {
                Toast.makeText(context, "Funciona el broadcast", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = IntentFilter()
        intent.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneBR, intent)


        binding.btnLocalBro.setOnClickListener {
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(intentFilter))
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalReceiver, IntentFilter(intentFilter))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(airplaneBR)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalReceiver)
    }
}