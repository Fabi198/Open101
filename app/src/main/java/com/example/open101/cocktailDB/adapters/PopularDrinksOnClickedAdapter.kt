package com.example.open101.cocktailDB.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.cocktailDB.entities.IngredientSimplifyView
import com.example.open101.databinding.ItemIngredientAndCocktailsSimpleViewBinding
import com.squareup.picasso.Picasso

class PopularDrinksOnClickedAdapter(private val listIngredients: ArrayList<IngredientSimplifyView>): RecyclerView.Adapter<PopularDrinksOnClickedAdapter.PopularDrinksOnClickedViewHolder>() {


    class PopularDrinksOnClickedViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemIngredientAndCocktailsSimpleViewBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(item: IngredientSimplifyView) {
            Picasso.get().load(item.strImageSource).centerCrop().resize(100,100).into(binding.ivIngredientSimplifyView)
            binding.tvIngredientSimplifyView.text = "${item.strIngredient} ${item.strMeasure}"
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularDrinksOnClickedViewHolder {
        return PopularDrinksOnClickedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient_and_cocktails_simple_view, parent, false))
    }

    override fun getItemCount(): Int {
        return listIngredients.size
    }

    override fun onBindViewHolder(holder: PopularDrinksOnClickedViewHolder, position: Int) {
        holder.bind(listIngredients[position])
    }
}