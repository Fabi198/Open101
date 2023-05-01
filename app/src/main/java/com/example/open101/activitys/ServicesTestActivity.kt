package com.example.open101.activitys

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.databinding.ActivityServicesTestBinding
import com.example.open101.services.MyBoundService
import com.example.open101.services.MyIntentService
import com.example.open101.services.MySecondBoundService
import com.example.open101.services.MyService

class ServicesTestActivity : AppCompatActivity() {


    /*
        NO OLVIDAR DECLARAR TODOS Y CADA UNO EN EL MANIFEST:

        <service android:name="****************"
                 android:exported="false"/>
     */

    /*
        - Started Service (servicio iniciado): servicio que corre en background/foreground, aun
          cuando la app no esta en uso.
        - Bound Service (servicio de enlace): servicio vinculado a una Activity, o a cualquier otro
          componente de Android, y que ademas interactua con el usuario.
        - Combinación entre Started Service y Bound Service.

     */

    /*
        Si se usan servicios en primer plano (aquellos que se ejecutan con la app cerrada, o que
        permanecen al hacerlo, se necesita SIEMPRE corroborar el SDK del sistema (si es mayor a 8.0
        se debe llamar al "startForegroundService()" y pedir permiso en el manifest a traves del
        "<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>")
     */


    private var mBound: Boolean = false
    private var boundService = MyBoundService()

    private var mBound2: Boolean = false
    private var boundSecondService = MySecondBoundService()

    private lateinit var binding: ActivityServicesTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicesTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent1 = Intent(this, MyIntentService::class.java)
        startService(intent1)

        val intent2 = Intent(this, MyService::class.java)
        startService(intent2)

        val intent3 = Intent(this, MyBoundService::class.java)
        bindService(intent3, connection, Context.BIND_AUTO_CREATE)

        val intent4 = Intent(this, MySecondBoundService::class.java)
        bindService(intent4, connection2, Context.BIND_AUTO_CREATE)

        binding.randomNumber.setOnClickListener {

                val num = boundService.getRandomNumber().toString()

                if (mBound) {
                    Toast.makeText(this, num, Toast.LENGTH_SHORT).show()
                }

            }

        binding.testButton.setOnClickListener {
            if (mBound2){
                Toast.makeText(this, "Se apreto este boton", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Entro por el else", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private val connection2 = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("Service", "El servicio se conecto a la interfaz de usuario")
            val binder2 = service as MySecondBoundService.MySecondBinder
            boundSecondService = binder2.getService()
            mBound2 = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound2 = false
            Log.i("Service", "No esta mas conectado con la interfaz de usuario")
        }

    }


    private val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("Service", "El servicio se conecto a la interfaz de usuario")
            val binder = service as MyBoundService.MyBinder
            boundService = binder.getService()
            mBound = true
        }


        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            Log.i("Service", "No esta conectado con la interfaz de usuario")
        }

    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
        unbindService(connection2)
        mBound2 = false
    }
}