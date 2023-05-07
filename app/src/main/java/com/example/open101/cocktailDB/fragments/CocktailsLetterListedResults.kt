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
import com.example.open101.databinding.FragmentCocktailsListedResultsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsLetterListedResults : Fragment(R.layout.fragment_cocktails_listed_results) {

    private lateinit var binding: FragmentCocktailsListedResultsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsListedResultsBinding.bind(view)

        lifecycleScope.launch(Dispatchers.IO) {
            val letter = arguments?.getString("letter", "No funco")
            val responseService = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("search.php?f=$letter")
            val cocktails = responseService.body()
            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful && cocktails?.Drinks != null) {
                    val adapter = PopularCocktailsAdapter(cocktails.Drinks) {
                        val bundle = Bundle()
                        bundle.putString("id", it)
                        val popuFrag = CocktailFullView()
                        popuFrag.arguments = bundle
                        requireActivity().supportFragmentManager.beginTransaction().add(
                            binding.popularcocktailslistedContainer.id,
                            popuFrag,
                            "PopularDrinksOnItemClickedFragment"
                        ).addToBackStack(null).commit()
                    }
                    binding.rvCocktailsListedResults.layoutManager =
                        GridLayoutManager(requireContext(), 4)
                    binding.rvCocktailsListedResults.adapter = adapter
                    binding.rvCocktailsListedResults.visibility = View.VISIBLE

                } else {
                    Toast.makeText(
                        requireContext(),
                        "No hay cocktails que empiecen con $letter",
                        Toast.LENGTH_SHORT
                    ).show()
                    @Suppress("DEPRECATION")
                    requireActivity().onBackPressed()
                }
            }
        }

    }

}