package com.example.open101.cocktailDB.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.open101.R
import com.example.open101.cocktailDB.RetrofitCocktail
import com.example.open101.cocktailDB.adapters.PopularCocktailsAdapter
import com.example.open101.databinding.FragmentIngredientFullViewBinding
import com.example.open101.translator.TranslateService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IngredientFullView : Fragment(R.layout.fragment_ingredient_full_view) {

    private lateinit var binding: FragmentIngredientFullViewBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIngredientFullViewBinding.bind(view)

        val ingredient = arguments?.getString("Ingredient")

        lifecycleScope.launch(Dispatchers.IO) {
            val translator = TranslateService
            val responseService = RetrofitCocktail.APICOCKTAILS.getIngredient("search.php?i=$ingredient")
            val ingredientDTO = responseService.body()
            val responseService2 = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("filter.php?i=$ingredient")
            val cocktails = responseService2.body()
            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful) {
                    Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/$ingredient-Small.png").fit().into(binding.ivIngredientBackground)
                    binding.tvIngredientTitle.text = ingredientDTO?.ingredient?.get(0)?.strIngredient
                    binding.tvType.text = "Type:\n${ingredientDTO?.ingredient?.get(0)?.strType}"
                    binding.tvABV.text = "ABV:\n${ingredientDTO?.ingredient?.get(0)?.strABV}"
                    binding.tvAlcoholic.text = "Alcoholic:\n${ingredientDTO?.ingredient?.get(0)?.strAlcohol}"
                    ingredientDTO?.ingredient?.get(0)?.strDescription?.let {
                        translator.englishSpanishTranslator.translate(it).addOnSuccessListener { translated ->
                            binding.tvIngredientDesc.text = translated
                        }
                    }
                    binding.rvIngredientUsedAt.layoutManager = GridLayoutManager(requireContext(), 4)
                    if (responseService2.isSuccessful) {
                        val listCocktails = cocktails?.Drinks
                        val adapter = PopularCocktailsAdapter(listCocktails!!) {
                            val bundle = Bundle()
                            bundle.putString("id", it)
                            val popuFrag = CocktailFullView()
                            popuFrag.arguments = bundle
                            requireActivity().supportFragmentManager.beginTransaction().add(
                                binding.popularcocktailslistedContainer.id,
                                popuFrag,
                                "PopularDrinksOnItemClickedFragment"
                            ).addToBackStack(null).commit()
                            binding.popularcocktailslistedContainer.visibility = View.VISIBLE
                        }
                        binding.rvIngredientUsedAt.adapter = adapter
                    }
                }
            }
        }

    }

}