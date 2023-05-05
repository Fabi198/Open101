package com.example.open101.cocktailDB.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.cocktailDB.entities.DrinkDTO
import com.example.open101.databinding.ItemPopularCocktailsBinding
import com.squareup.picasso.Picasso

class PopularCocktailsAdapter(private var listDrinks: List<DrinkDTO>, private val onClickItem: (String) -> Unit): RecyclerView.Adapter<PopularCocktailsAdapter.PopularCocktailsViewHolder>() {

    class PopularCocktailsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemPopularCocktailsBinding.bind(view)

        fun bind(item: DrinkDTO, onClickItem: (String) -> Unit) {
            //Picasso.get().load(item.strDrinkThumb).resize(70, 70).into(binding.ivPopCocktail)
            Picasso.get().load(item.strDrinkThumb).centerCrop().resize(140, 140).into(binding.ivPopCocktail)
            binding.tvPopCocktail.text = item.strDrink
            itemView.setOnClickListener { onClickItem(item.idDrink.toString()) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCocktailsViewHolder {
        return PopularCocktailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_popular_cocktails, parent, false))
    }

    override fun getItemCount(): Int {
        return listDrinks.size
    }

    override fun onBindViewHolder(holder: PopularCocktailsViewHolder, position: Int) {
        val item = listDrinks[position]
        holder.bind(item, onClickItem)
    }

}