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
import com.example.open101.databinding.FragmentCocktailsCategoryListedResultsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsCategoryListedResults : Fragment(R.layout.fragment_cocktails_category_listed_results) {

    private lateinit var binding: FragmentCocktailsCategoryListedResultsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsCategoryListedResultsBinding.bind(view)
        val category = arguments?.getString("Category", "No funco")
        binding.cvCategoryText.text = category?.replace("_", " ")

        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("filter.php?c=$category")
            val cocktails = responseService.body()
            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful && cocktails?.Drinks != null) {
                    val adapter = PopularCocktailsAdapter(cocktails.Drinks) {
                        val bundle = Bundle()
                        bundle.putString("id", it)
                        val popuFrag = CocktailFullView()
                        popuFrag.arguments = bundle
                        requireActivity().supportFragmentManager.beginTransaction().replace(
                            binding.cocktailsCategorylistedContainer.id,
                            popuFrag,
                            "CocktailsCategoryListedResultsFragment"
                        ).addToBackStack(null).commit()
                    }
                    binding.rvCocktailsCategoryListedResults.layoutManager =
                        GridLayoutManager(requireContext(), 4)
                    binding.rvCocktailsCategoryListedResults.adapter = adapter
                    binding.rvCocktailsCategoryListedResults.visibility = View.VISIBLE

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