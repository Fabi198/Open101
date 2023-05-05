package com.example.open101.cocktailDB.fragments

import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.cocktailDB.RetrofitCocktail
import com.example.open101.cocktailDB.adapters.PopularDrinksOnClickedAdapter
import com.example.open101.cocktailDB.entities.IngredientSimplifyView
import com.example.open101.databinding.FragmentPopularDrinksOnItemClickedBinding
import com.example.open101.translator.TranslateService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PopularDrinksOnItemClicked : Fragment(R.layout.fragment_popular_drinks_on_item_clicked) {

    private lateinit var binding: FragmentPopularDrinksOnItemClickedBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPopularDrinksOnItemClickedBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("id", "0")
        lifecycleScope.launch {
            if (id != "0") {
                val responseService = RetrofitCocktail.APICOCKTAILS.getPopularDrinks("lookup.php?i=$id")
                val cocktail = responseService.body()!!.Drinks[0]
                withContext(Dispatchers.Main) {
                    val translator = TranslateService
                    if (cocktail.strInstructionsES != null) {
                        binding.tvDesc.text = "Instrucciones: ${cocktail.strInstructionsES}"
                    } else {
                        cocktail.strInstructionsEN?.let {
                            translator.englishSpanishTranslator.translate(it).addOnSuccessListener { itES ->
                                binding.tvDesc.text = "Instrucciones: $itES"
                            }
                        }
                    }
                    Picasso.get().load(cocktail.strDrinkThumb).fit().into(binding.ivBackground)
                    binding.tvTitleDrink.text = cocktail.strDrink
                    binding.tvGlass.text = cocktail.strGlass
                    binding.tvAlcoholic.text = cocktail.strAlcoholic
                    binding.tvCategory.text = cocktail.strCategory
                    binding.rvPopDrinkOnClicked.layoutManager = LinearLayoutManager(requireContext())
                    val listIngredients = ArrayList<IngredientSimplifyView>()
                    if (cocktail.strIngredient1 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient1,
                            cocktail.strMeasure1,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient1}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient2 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient2,
                            cocktail.strMeasure2,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient2}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient3 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient3,
                            cocktail.strMeasure3,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient3}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient4 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient4,
                            cocktail.strMeasure4,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient4}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient5 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient5,
                            cocktail.strMeasure5,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient5}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient6 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient6,
                            cocktail.strMeasure6,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient6}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient7 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient7,
                            cocktail.strMeasure7,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient7}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient8 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient8,
                            cocktail.strMeasure8,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient8}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient9 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient9,
                            cocktail.strMeasure9,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient9}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient10 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient10,
                            cocktail.strMeasure10,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient10}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient11 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient11,
                            cocktail.strMeasure11,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient11}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient12 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient12,
                            cocktail.strMeasure12,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient12}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient13 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient13,
                            cocktail.strMeasure13,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient13}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient14 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient14,
                            cocktail.strMeasure14,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient14}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    if (cocktail.strIngredient15 != null) {
                        val ingredient1 = IngredientSimplifyView(
                            cocktail.strIngredient15,
                            cocktail.strMeasure15,
                            "https://www.thecocktaildb.com/images/ingredients/${cocktail.strIngredient15}-Small.png"
                        )
                        listIngredients.add(ingredient1)
                    }
                    val adapter = PopularDrinksOnClickedAdapter(listIngredients)
                    binding.rvPopDrinkOnClicked.adapter = adapter
                }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error en la base de datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        binding.btnBack.setOnClickListener {
            @Suppress("DEPRECATION")
            requireActivity().onBackPressed()
        }
    }



    //Si alguna vez necesito redondear una imagen, este metodo no se puede hacer en el main thread
    /*
    private fun getRoundedCornerBitmap(bitmap: Bitmap, square: Boolean): Bitmap? {
        var width = 0
        var height = 0
        if (square) {
            if (bitmap.width < bitmap.height) {
                width = bitmap.width
                height = bitmap.width
            } else {
                width = bitmap.height
                height = bitmap.height
            }
        } else {
            height = bitmap.height
            width = bitmap.width
        }
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, width, height)
        val rectF = RectF(rect)
        val roundPx = 90f
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

     */


}