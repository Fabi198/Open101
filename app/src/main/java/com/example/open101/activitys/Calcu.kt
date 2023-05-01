package com.example.open101.activitys

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.databinding.ActivityCalcuBinding

class Calcu : AppCompatActivity() {

    private lateinit var binding: ActivityCalcuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarCalcu)
        supportActionBar?.title = null
        initializeBtnListener()
    }


    private fun initializeBtnListener() {
        binding.btnSumar.setOnClickListener {
            val numero1 = getTextFromEditText(binding.etNumero1)
            val numero2 = getTextFromEditText(binding.etNumero2)

            if (emptyField(numero1, numero2)) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val resultado = numero1.toDouble() + numero2.toDouble()
                val sumResult = "El resultado de la suma es: $resultado"
                binding.txtResultado.text = sumResult
            }




        }

        binding.btnRestar.setOnClickListener {
            val numero1 = getTextFromEditText(binding.etNumero1)
            val numero2 = getTextFromEditText(binding.etNumero2)
            if (emptyField(numero1, numero2)) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val resultado = numero1.toDouble() - numero2.toDouble()
                val restResult = "El resultado de la resta es: $resultado"
                binding.txtResultado.text = restResult
            }
        }

        binding.btnMulti.setOnClickListener {
            val numero1 = getTextFromEditText(binding.etNumero1)
            val numero2 = getTextFromEditText(binding.etNumero2)

            if (emptyField(numero1, numero2)) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val resultado = numero1.toDouble() * numero2.toDouble()
                val multiResult = "El resultado de la multiplicacion es: $resultado"
                binding.txtResultado.text = multiResult
            }
        }

        binding.btnDiv.setOnClickListener {
            val numero1 = getTextFromEditText(binding.etNumero1)
            val numero2 = getTextFromEditText(binding.etNumero2)

            if (emptyField(numero1, numero2)) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val resultado = numero1.toDouble() / numero2.toDouble()
                val divResult = "El resultado de la division es: $resultado"
                binding.txtResultado.text = divResult
            }
        }

        binding.btnPor.setOnClickListener {
            val numero1 = getTextFromEditText(binding.etNumero1)
            val numero2 = getTextFromEditText(binding.etNumero2)

            if (emptyField(numero1, numero2)) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val resultado = (numero2.toDouble()*numero1.toDouble()) / 100
                val porResult = "El $numero2% de $numero1 es $resultado"
                binding.txtResultado.text = porResult
            }
        }

        binding.floatingActionButton.setOnClickListener {
            binding.etNumero1.setText("")
            binding.etNumero2.setText("")
            val result = "El resultado es:"
            binding.txtResultado.text = result
        }

    }

    private fun emptyField(numero1: String, numero2:String): Boolean {
        return numero1.isEmpty() || numero2.isEmpty()
    }

    private fun getTextFromEditText(editText: EditText): String {
        return editText.text.toString()
    }
}