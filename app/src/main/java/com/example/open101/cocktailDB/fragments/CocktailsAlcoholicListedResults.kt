package com.example.open101.cocktailDB.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.open101.R
import com.example.open101.cocktailDB.RetrofitCocktail
import com.example.open101.cocktailDB.adapters.PopularCocktailsAdapter
import com.example.open101.databinding.FragmentCocktailsAlcoholicListedResultsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CocktailsAlcoholicListedResults : Fragment(R.layout.fragment_cocktails_alcoholic_listed_results) {

    private lateinit var binding: FragmentCocktailsAlcoholicListedResultsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsAlcoholicListedResultsBinding.bind(view)
        val alcoholic = arguments?.getString("Alcoholic", "No funco")
        binding.cvAlcoholicText.text = alcoholic?.replace("_", " ")

        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("filter.php?a=$alcoholic")
            val cocktails = responseService.body()
            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful && cocktails?.Drinks != null) {
                    val adapter = PopularCocktailsAdapter(cocktails.Drinks) {
                        val bundle = Bundle()
                        bundle.putString("id", it)
                        val popuFrag = CocktailFullView()
                        popuFrag.arguments = bundle
                        requireActivity().supportFragmentManager.beginTransaction().replace(
                            binding.cocktailsAlcoholiclistedContainer.id,
                            popuFrag,
                            "cocktailsAlcoholicListedFragment"
                        ).addToBackStack(null).commit()
                    }
                    binding.rvCocktailsAlcoholicListedResults.layoutManager =
                        GridLayoutManager(requireContext(), 4)
                    binding.rvCocktailsAlcoholicListedResults.adapter = adapter
                    binding.rvCocktailsAlcoholicListedResults.visibility = View.VISIBLE

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