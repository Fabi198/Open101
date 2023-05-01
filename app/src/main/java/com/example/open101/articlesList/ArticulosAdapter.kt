package com.example.open101.articlesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.databinding.ItemArticleBinding

class ArticulosAdapter (private val articlesList: List<Articulos>): RecyclerView.Adapter<ArticulosAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemArticleBinding.bind(view)

        fun bind(item: Articulos) {
            binding.tvCode.text = item.codigo.toString()
            binding.tvDesc.text = item.descripcion
            binding.tvPrice.text = item.price.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(com.example.open101.R.layout.item_article, parent, false))
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = articlesList[position]
        holder.bind(item)

    }
}