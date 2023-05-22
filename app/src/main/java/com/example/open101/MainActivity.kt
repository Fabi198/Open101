package com.example.open101

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.activitys.*
import com.example.open101.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.title = null
        setupButtons()
    }

    private fun setupButtons() {
        binding.btnTC.setOnClickListener { startActivity(Intent(this, Temperaturecalculator::class.java)) }
        binding.btnCalcu.setOnClickListener { startActivity(Intent(this, Calcu::class.java)) }
        binding.btnDoggy.setOnClickListener { startActivity(Intent(this, DogList::class.java)) }
        binding.btnArticles.setOnClickListener { startActivity(Intent(this, ArticulosActivity::class.java)) }
        binding.btnBooks.setOnClickListener { startActivity(Intent(this, BookLoginActivity::class.java)) }
        binding.btnSuper.setOnClickListener { startActivity(Intent(this, Superhero::class.java)) }
        binding.btnFragments.setOnClickListener { startActivity(Intent(this, Fragment101::class.java)) }
        binding.btnUserDB.setOnClickListener { startActivity(Intent(this, UserDB::class.java)) }
        binding.btnServices.setOnClickListener { startActivity(Intent(this, ServicesTestActivity::class.java)) }
        binding.btnBroad.setOnClickListener { startActivity(Intent(this, BroadcastActivity::class.java)) }
        binding.btnSimpsonsDB.setOnClickListener { startActivity(Intent(this, SimpsonsDB::class.java)) }
        binding.btnNASADB.setOnClickListener { startActivity(Intent(this, NasaDB::class.java)) }
        binding.btnAlarms.setOnClickListener { startActivity(Intent(this, AlarmsTestActivity::class.java)) }
        binding.btnCockDB.setOnClickListener { startActivity(Intent(this, CocktailDB::class.java)) }
        binding.btnMallweb.setOnClickListener { startActivity(Intent(this, MallWeb::class.java)) }
    }
}