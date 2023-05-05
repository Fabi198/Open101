package com.example.open101.cocktailDB.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.cocktailDB.entities.DrinkDTO
import com.example.open101.databinding.ItemIngredientAndCocktailsSimpleViewBinding
import com.example.open101.databinding.ItemPopularCocktailsBinding
import com.squareup.picasso.Picasso

class CocktailsSimpleListAdapter(private val listCocktails: List<DrinkDTO>):RecyclerView.Adapter<CocktailsSimpleListAdapter.CocktailsSimpleListViewHolder>() {

    class CocktailsSimpleListViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemPopularCocktailsBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(item: DrinkDTO) {
            Picasso.get().load(item.strDrinkThumb).centerCrop().resize(100,100).into(binding.ivPopCocktail)
            binding.tvPopCocktail.text = item.strDrink
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsSimpleListViewHolder {
        return CocktailsSimpleListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_popular_cocktails, parent, false))
    }

    override fun getItemCount(): Int {
        return listCocktails.size
    }

    override fun onBindViewHolder(holder: CocktailsSimpleListViewHolder, position: Int) {
        holder.bind(listCocktails[position])
    }
}