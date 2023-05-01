package com.example.open101.nasaDB

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.ItemApodCountBinding
import com.example.open101.nasaDB.entities.APOD
import com.example.open101.translator.TranslateService
import com.squareup.picasso.Picasso

class NASAAdapter(private val listAPOD: List<APOD>): RecyclerView.Adapter<NASAAdapter.NASAViewHolder>() {

    class NASAViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemApodCountBinding.bind(view)
        private var translator = TranslateService

        @SuppressLint("SetTextI18n")
        fun bind(item: APOD) {
            Picasso.get().load(item.image).centerCrop(350).resize(650, 450).into(binding.ivAPOD)
            binding.tvDate.text = "Date: ${item.date}"
            translator.englishSpanishTranslator.translate(item.title).addOnSuccessListener {
                binding.tvTitle.text = "Title: ${item.title} ($it)"
            }
            binding.tvAuthor.text = "Author: ${item.author}"
            translator.englishSpanishTranslator.translate(item.desc).addOnSuccessListener {
                binding.tvDesc.text = it
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NASAViewHolder {
        return NASAViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_apod_count, parent, false))
    }

    override fun getItemCount(): Int {
        return listAPOD.size
    }

    override fun onBindViewHolder(holder: NASAViewHolder, position: Int) {
        val item = listAPOD[position]
        holder.bind(item)
    }


}