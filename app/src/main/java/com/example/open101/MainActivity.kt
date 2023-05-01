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
        binding.btnTC.setOnClickListener {
            val intent = Intent(this, Temperaturecalculator::class.java)
            startActivity(intent)
        }

        binding.btnCalcu.setOnClickListener {
            val intent = Intent(this, Calcu::class.java)
            startActivity(intent)
        }

        binding.btnDoggy.setOnClickListener {
            val intent = Intent(this, DogList::class.java)
            startActivity(intent)
        }

        binding.btnArticles.setOnClickListener {
            val intent = Intent(this, ArticulosActivity::class.java)
            startActivity(intent)
        }

        binding.btnBooks.setOnClickListener {
            val intent = Intent(this, BookLoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSuper.setOnClickListener {
            val intent = Intent(this, Superhero::class.java)
            startActivity(intent)
        }

        binding.btnFragments.setOnClickListener {
            val intent = Intent(this, Fragment101::class.java)
            startActivity(intent)
        }

        binding.btnUserDB.setOnClickListener {
            val intent = Intent(this, UserDB::class.java)
            startActivity(intent)
        }

        binding.btnServices.setOnClickListener {
            val intent = Intent(this, ServicesTestActivity::class.java)
            startActivity(intent)
        }

        binding.btnBroad.setOnClickListener {
            val intent = Intent(this, BroadcastActivity::class.java)
            startActivity(intent)
        }

        binding.btnSimpsonsDB.setOnClickListener {
            val intent = Intent(this, SimpsonsDB::class.java)
            startActivity(intent)
        }

        binding.btnNASADB.setOnClickListener {
            val intent = Intent(this, NasaDB::class.java)
            startActivity(intent)
        }

        binding.btnAlarms.setOnClickListener {
            val intent = Intent(this, AlarmsTestActivity::class.java)
            startActivity(intent)
        }

        binding.btnCockDB.setOnClickListener {
            val intent = Intent(this, CocktailDB::class.java)
            startActivity(intent)
        }
    }
}