package com.example.open101.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.open101.databinding.ActivityNasadbBinding
import com.example.open101.nasaDB.APODCountFragment
import com.example.open101.nasaDB.APODFragment
import com.example.open101.nasaDB.BackListener

class NasaDB : AppCompatActivity(), BackListener {

    private lateinit var binding: ActivityNasadbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNasadbBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAPOD.setOnClickListener {
            binding.ivNASA.visibility = View.GONE
            binding.btnAPOD.visibility = View.GONE
            supportFragmentManager.beginTransaction().add(binding.apodContainer.id, APODFragment(), "APOD Fragment").commit()
        }

        binding.btnGetPart2.setOnClickListener {
            openAPODCountFragment()
        }

        binding.btnGet.setOnClickListener {
            openAPODCountFragment()
        }


    }

    private fun openAPODCountFragment() {
        val bundle = Bundle()
        bundle.putString("Count", binding.scvNASA.text)
        val apod = APODCountFragment()
        apod.arguments = bundle
        binding.scvNASA.clearText()
        binding.ivNASA.visibility = View.GONE
        binding.btnAPOD.visibility = View.GONE
        supportFragmentManager.beginTransaction().add(binding.apodContainer.id, apod, "APOD Count Fragment").commit()
    }

    override fun onBack() {
        binding.ivNASA.visibility = View.VISIBLE
        binding.btnAPOD.visibility = View.VISIBLE
    }
}