package com.example.open101.activitys

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import com.example.open101.alarms.AlarmReceiver
import com.example.open101.databinding.ActivityAlarmsTestBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class AlarmsTestActivity : AppCompatActivity() {


    // NO OLVIDAR DECLARAR EN EL MANIFEST: <receiver android:name="-la clase Receiver de la alarma-"/>

    /*
        Alarmas de paso de tiempo (Por ejemplo: Dentro de 1 hora/minuto/segundo):
        ELAPSED_REALTIME: Si la pantalla esta apagada, la alarma no se lanzará hasta que se encienda
        ELAPSED_REALTIME_WAKEUP: Si la pantalla esta apagada, la alarma se lanzará de todas maneras

        Alarmas de hora exacta (Se lanzan a determinada hora/minuto/segundo):
        RTC: Si se encuentra apagada, no llegará hasta que se encienda
        RTC_WAKEUP: Si la pantalla se encuentra apagada, la alarma se lanzará de todas maneras

     */

    private lateinit var binding: ActivityAlarmsTestBinding

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAlarmsTestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmManager2 = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)


        // No se puede llamar 2 veces a la misma alarma con metodos distintos
        alarmSetting(alarmManager, pendingIntent)
        exactAlarmSetting(alarmManager2, pendingIntent)

        binding.tvTestingAlarms.setOnClickListener {
            Snackbar.make(binding.clAlarms, "Hola", Snackbar.LENGTH_SHORT)
                .setAction("Aceptar"){
                    it.visibility = View.VISIBLE
                }
                .show()
        }


        binding.til.counterMaxLength = 14

    }


    private fun exactAlarmSetting(alarmManager: AlarmManager, pendingIntent: PendingIntent) {

        Log.i("Alarm322", "Seteamos la alarma")

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 22)
        calendar.set(Calendar.MINUTE, 19)

        alarmManager.set(
            AlarmManager.RTC,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    private fun alarmSetting(alarmManager: AlarmManager, pendingIntent: PendingIntent) {
        Log.i("Alarm321", "Seteamos la alarma")

        alarmManager.set(
            AlarmManager.ELAPSED_REALTIME,
            SystemClock.elapsedRealtime() + 10000,
            pendingIntent
        )
    }
}