package com.example.open101.cocktailDB.adapters


import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.cocktailDB.entities.IngredientSimplifyView
import com.example.open101.databinding.ItemIngredientAndCocktailsSimpleViewBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt


class PopularDrinksOnClickedAdapter(private val listIngredients: ArrayList<IngredientSimplifyView>, private val onClickItem: (String) -> Unit): RecyclerView.Adapter<PopularDrinksOnClickedAdapter.PopularDrinksOnClickedViewHolder>() {


    class PopularDrinksOnClickedViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemIngredientAndCocktailsSimpleViewBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(item: IngredientSimplifyView, onClickItem: (String) -> Unit) {
            Picasso.get().load(item.strImageSource).centerCrop().resize(100,100).into(binding.ivIngredientSimplifyView)
            binding.tvIngredientSimplifyView.text = "${item.strIngredient} ${item.strMeasure}"
            itemView.setOnClickListener { onClickItem(item.strIngredient.toString()) }
            binding.etCalculatorOz.isFocusableInTouchMode = true
            binding.etCalculatorOz.requestFocus()
            binding.etCalculatorOz.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    @Suppress("DEPRECATED_IDENTITY_EQUALS")
                    if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        val result = (binding.etCalculatorOz.text.toString().toDouble() * 29.574)
                        val roundoff = (result * 100.0).roundToInt().toDouble() / 100.0
                        binding.tvCalculatorOz.text = "$roundoff ml"
                        binding.etCalculatorOz.setText("")
                        return true
                    }
                    return false
                }
            })
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularDrinksOnClickedViewHolder {
        return PopularDrinksOnClickedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient_and_cocktails_simple_view, parent, false))
    }

    override fun getItemCount(): Int {
        return listIngredients.size
    }

    override fun onBindViewHolder(holder: PopularDrinksOnClickedViewHolder, position: Int) {
        holder.bind(listIngredients[position], onClickItem)
    }
}