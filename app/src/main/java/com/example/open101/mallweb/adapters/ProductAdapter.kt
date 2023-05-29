package com.example.open101.mallweb.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.ItemMallwebPrintersBinding
import com.example.open101.mallweb.entities.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val listProduct: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMallwebPrintersBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(i: Product) {
            val s = "$"
            if (i.image != 0) {
                Picasso.get().load(i.image).fit().into(binding.ivPrintImage)
            }
            binding.tvPrintTitle.text = i.name
            binding.tvPrice.text = "U${s}S ${i.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_mallweb_printers, parent, false))
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }
}