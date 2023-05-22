package com.example.open101.mallweb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.ItemMallwebPrintersBinding
import com.example.open101.mallweb.entities.Print
import com.squareup.picasso.Picasso

class PrintersAdapter(private val listPrinters: Array<Print>): RecyclerView.Adapter<PrintersAdapter.PrintersViewHolder>() {





    class PrintersViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMallwebPrintersBinding.bind(view)

        fun bind(item: Print) {
            Picasso.get().load(item.iv).fit().into(binding.ivPrintImage)
            binding.tvPrintTitle.text = item.tv
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrintersViewHolder {
        return PrintersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_mallweb_printers, parent, false))
    }

    override fun getItemCount(): Int {
        return listPrinters.size
    }

    override fun onBindViewHolder(holder: PrintersViewHolder, position: Int) {
        holder.bind(listPrinters[position])
    }
}