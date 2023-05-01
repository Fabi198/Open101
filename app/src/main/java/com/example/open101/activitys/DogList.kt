package com.example.open101.activitys

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.dogList.APIDogService
import com.example.open101.dogList.DogAdapter
import com.example.open101.databinding.ActivityDogListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogList : AppCompatActivity() {

    private lateinit var binding: ActivityDogListBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        val suggestions = setSuggestions()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, suggestions)
        binding.etBreed.threshold = 0
        binding.etBreed.setAdapter(adapter)

        binding.btnBreed.setOnClickListener {
            if (binding.rvDogs.visibility == View.VISIBLE) {
                binding.rvDogs.visibility = View.GONE
            }
            binding.progressDog.visibility = View.VISIBLE
            val breed = binding.etBreed.text.toString()
            searchByBreed(breed.lowercase())
            binding.etBreed.text.clear()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages) {
                item -> Toast.makeText(this, item, Toast.LENGTH_SHORT).show()
        }
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByBreed(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIDogService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    val images = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                    binding.progressDog.visibility = View.GONE
                    binding.rvDogs.visibility = View.VISIBLE
                }else{
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun setSuggestions(): Array<String> {
        return arrayOf(
            "affenpinscher",
            "african",
            "airedale",
            "akita",
            "appenzeller",
            "australian",
            "basenji",
            "beagle",
            "bluetick",
            "borzoi",
            "bouvier",
            "boxer",
            "brabancon",
            "briard",
            "buhund",
            "bulldog",
            "bullterrier",
            "cattledog",
            "chihuahua",
            "chow",
            "clumber",
            "cockapoo",
            "collie",
            "coonhound",
            "corgi",
            "cotondetulear",
            "dachshund",
            "dalmatian",
            "dane",
            "deerhound",
            "dhole",
            "dingo",
            "doberman",
            "elkhound",
            "entlebucher",
            "eskimo",
            "finnish",
            "frise",
            "germanshepherd",
            "greyhound",
            "groenendael",
            "havanese",
            "hound",
            "husky",
            "keeshond",
            "kelpie",
            "komondor",
            "kuvasz",
            "labradoodle",
            "labrador",
            "leonberg",
            "lhasa",
            "malamute",
            "malinois",
            "maltese",
            "mastiff",
            "mexicanhairless",
            "mix",
            "mountain",
            "newfoundland",
            "otterhound",
            "ovcharka",
            "papillon",
            "pekinese",
            "pembroke",
            "pinscher",
            "pitbull",
            "pointer",
            "pomeranian",
            "poodle",
            "pug",
            "puggle",
            "pyrenees",
            "redbone",
            "retriever",
            "ridgeback",
            "rottweiler",
            "saluki",
            "samoyed",
            "schipperke",
            "schnauzer",
            "segugio",
            "setter",
            "sharpei",
            "sheepdog",
            "shiba",
            "shihtzu",
            "spaniel",
            "spitz",
            "springer",
            "stbernard",
            "terrier",
            "tervuren",
            "vizsla",
            "waterdog",
            "spanish",
            "weimaraner",
            "whippet",
            "wolfhound"
        )
    }
}