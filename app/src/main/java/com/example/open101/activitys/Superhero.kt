package com.example.open101.activitys

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.databinding.ActivitySuperheroBinding
import com.example.open101.superhero.RetrofitSuperheroClient
import com.example.open101.superhero.SuperheroAdapter
import com.example.open101.superhero.entities.SuperheroDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Superhero : AppCompatActivity() {

    private lateinit var binding: ActivitySuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSuperhero)
        binding.toolbarSuperhero.title = null
        supportActionBar?.title = null
        binding.rvSuper.layoutManager = LinearLayoutManager(this)


        binding.btnId.setOnClickListener {
            if (binding.etId.text.isNullOrEmpty()) {
                Toast.makeText(this, "Debe completar el campo correspondiente", Toast.LENGTH_LONG).show()
            } else {
                binding.rvSuper.visibility = View.GONE
                binding.pbSuper.visibility = View.VISIBLE
                searchByID(binding.etId.text.toString().lowercase())
                binding.etId.setText("")
                hideKeyboard()
            }
        }

        binding.btnBreed.setOnClickListener {
            if (binding.etBreed.text.isNullOrEmpty()) {
                Toast.makeText(this, "Debe completar el campo correspondiente", Toast.LENGTH_LONG).show()
            } else {
                binding.rvSuper.visibility = View.GONE
                binding.pbSuper.visibility = View.VISIBLE
                searchByName(binding.etBreed.text.toString().lowercase())
                binding.etBreed.setText("")
                hideKeyboard()
            }
        }

    }

    private fun showErrorID() {
        Toast.makeText(this, "ID inexistente", Toast.LENGTH_LONG).show()
    }

    private fun showErrorName() {
        Toast.makeText(this, "Nombre inexistente", Toast.LENGTH_LONG).show()
    }

    private fun searchByName(s: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitSuperheroClient.APISuperheroService.getName("search/${s.lowercase()}")
            val superHero = responseService.body()

            withContext(Dispatchers.Main) {
                if (superHero?.response == "success") {
                    val adapter = SuperheroAdapter(superHero.results)
                    binding.rvSuper.adapter = adapter
                    binding.pbSuper.visibility = View.GONE
                    binding.rvSuper.visibility = View.VISIBLE
                } else {
                    showErrorName()
                }
            }
        }
    }

    private fun searchByID(s: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitSuperheroClient.APISuperheroService.getID(s)
            val superHero = responseService.body()

            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful && !superHero?.id.isNullOrEmpty()) {
                    val super2 = ArrayList<SuperheroDto>()
                    if (superHero != null) {
                        super2.add(superHero)
                    }
                    val adapter = SuperheroAdapter(super2)
                    binding.rvSuper.adapter = adapter
                    binding.pbSuper.visibility = View.GONE
                    binding.rvSuper.visibility = View.VISIBLE
                } else {
                    showErrorID()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}