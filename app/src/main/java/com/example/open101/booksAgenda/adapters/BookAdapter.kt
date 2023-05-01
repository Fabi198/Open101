package com.example.open101.booksAgenda.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.booksAgenda.entities.Book
import com.example.open101.databinding.ItemBookBinding

class BookAdapter (private var listBooks: ArrayList<Book>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(v: View): RecyclerView.ViewHolder(v) {

        private val binding = ItemBookBinding.bind(v)

        fun bind(item: Book) {
            binding.tvNameBookList.text = item.name
            binding.tvAutorList.text = item.author
            binding.tvGenderList.text = item.gender
            binding.tvYearList.text = item.year
            binding.tvUnitsList.text = item.units.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false))
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = listBooks[position]
        holder.bind(item)
    }
}