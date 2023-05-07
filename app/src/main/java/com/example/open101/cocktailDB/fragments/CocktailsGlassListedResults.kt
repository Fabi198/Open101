package com.example.open101.cocktailDB.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.open101.R
import com.example.open101.cocktailDB.RetrofitCocktail
import com.example.open101.cocktailDB.adapters.PopularCocktailsAdapter
import com.example.open101.databinding.FragmentCocktailsGlassListedResultsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsGlassListedResults : Fragment(R.layout.fragment_cocktails_glass_listed_results) {

    private lateinit var binding: FragmentCocktailsGlassListedResultsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsGlassListedResultsBinding.bind(view)
        val glass = arguments?.getString("Glass", "No funco")
        binding.cvGlassText.text = glass?.replace("_", " ")

        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("filter.php?g=$glass")
            val cocktails = responseService.body()
            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful && cocktails?.Drinks != null) {
                    val adapter = PopularCocktailsAdapter(cocktails.Drinks) {
                        val bundle = Bundle()
                        bundle.putString("id", it)
                        val popuFrag = CocktailFullView()
                        popuFrag.arguments = bundle
                        requireActivity().supportFragmentManager.beginTransaction().add(
                            binding.cocktailsGlasslistedContainer.id,
                            popuFrag,
                            "cocktailsGlassListedFragment"
                        ).addToBackStack(null).commit()
                    }
                    binding.rvCocktailsGlassListedResults.layoutManager =
                        GridLayoutManager(requireContext(), 4)
                    binding.rvCocktailsGlassListedResults.adapter = adapter
                    binding.rvCocktailsGlassListedResults.visibility = View.VISIBLE

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error en la base de datos",
                        Toast.LENGTH_SHORT
                    ).show()
                    @Suppress("DEPRECATION")
                    requireActivity().onBackPressed()
                }
            }
        }

    }


}