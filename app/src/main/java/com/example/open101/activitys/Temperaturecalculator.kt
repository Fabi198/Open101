package com.example.open101.activitys

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.databinding.ActivityTemperatureCalculatorBinding

class Temperaturecalculator : AppCompatActivity() {

    private lateinit var binding: ActivityTemperatureCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemperatureCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarTC)
        supportActionBar?.title = null

        binding.btnResult.setOnClickListener {
            if (binding.etN1.text.toString().isNotEmpty()) {
                if (binding.btnN2Celsius.isChecked) {
                    btnCelsius(binding.etN1.text.toString().toDouble())
                } else if (binding.btnN2Fahrenheit.isChecked) {
                    btnFahrenheit(binding.etN1.text.toString().toDouble())
                } else if (binding.btnN2Kelvin.isChecked) {
                    btnKelvin(binding.etN1.text.toString().toDouble())
                }
                falseChecked()
                binding.etN1.text?.clear()
            } else {
                Toast.makeText(this, "Escriba al menos un numero", Toast.LENGTH_LONG).show()
            }

        }
        binding.btnClear.setOnClickListener {
            val result = "El resultado es:"
            binding.tvResult.text = result
            falseChecked()
        }
    }

    private fun falseChecked() {
        binding.btnN1Celsius.isChecked = false
        binding.btnN1Fahrenheit.isChecked = false
        binding.btnN1Kelvin.isChecked = false
        binding.btnN2Celsius.isChecked = false
        binding.btnN2Fahrenheit.isChecked = false
        binding.btnN2Kelvin.isChecked = false
    }

    private fun btnCelsius(n1: Double) : Double {
        var resultado = 0.0
        if (binding.btnN1Celsius.isChecked) {
            resultado = n1/1
            val result = "$n1 grados Celsius, son $resultado grados Celsius"
            binding.tvResult.text = result
        } else if (binding.btnN1Kelvin.isChecked) {
            resultado = n1 - 273.15
            val result = "$n1 grados Kelvin, son $resultado grados Celsius"
            binding.tvResult.text = result
        } else if (binding.btnN1Fahrenheit.isChecked) {
            resultado = (n1 - 32) * 5/9
            val result = "$n1 grados Fahrenheit, son $resultado grados Celsius"
            binding.tvResult.text = result
        }
        return resultado
    }

    private fun btnFahrenheit(n1: Double): Double {
        var resultado = 0.0
        if (binding.btnN1Fahrenheit.isChecked) {
            resultado = n1/1
            val result = "$n1 grados Fahrenheit son $resultado grados Fahrenheit"
            binding.tvResult.text = result
        } else if (binding.btnN1Kelvin.isChecked) {
            resultado = (n1 - 273.15) * 9/5 + 32
            val result = "$n1 grados Kelvin son $resultado grados Fahrenheit"
            binding.tvResult.text = result
        } else if (binding.btnN1Celsius.isChecked) {
            resultado = (n1 * 9/5) + 32
            val result = "$n1 grados Celsius son $resultado grados Fahrenheit"
            binding.tvResult.text = result
        }
        return resultado
    }

    private fun btnKelvin(n1: Double): Double {
        var resultado = 0.0
        if (binding.btnN1Kelvin.isChecked) {
            resultado = n1/1
            val result = "$n1 grados Kelvin son $resultado grados Kelvin"
            binding.tvResult.text = result
        } else if (binding.btnN1Celsius.isChecked) {
            resultado = n1 + 273.15
            val result = "$n1 grados Celsius son $resultado grados Kelvin"
            binding.tvResult.text = result
        } else if (binding.btnN1Fahrenheit.isChecked) {
            resultado = (n1 - 32) * 5/9 + 273.15
            val result = "$n1 grados Fahrenheit son $resultado grados Kelvin"
            binding.tvResult.text = result
        }
        return resultado
    }
}