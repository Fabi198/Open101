package com.example.open101.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.databinding.ActivitySimpsonsDbBinding
import com.example.open101.simpsons.RetrofitEpisodes
import com.example.open101.simpsons.SimpsonsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SimpsonsDB : AppCompatActivity() {

    private lateinit var binding: ActivitySimpsonsDbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpsonsDbBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvSimpsons.layoutManager = LinearLayoutManager(this)


        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitEpisodes.APIEpisodes.getEpisodes("episodes")
            val simpsonsEpisodes = responseService.body()
            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful) {
                    val adapter = SimpsonsAdapter(simpsonsEpisodes!!)
                    binding.rvSimpsons.adapter = adapter
                    binding.pbSimpsons.visibility = View.GONE
                    binding.rvSimpsons.visibility = View.VISIBLE
                } else {
                    Toast.makeText(applicationContext, "Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}