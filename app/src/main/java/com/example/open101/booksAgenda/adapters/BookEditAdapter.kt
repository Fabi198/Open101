package com.example.open101.booksAgenda.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.booksAgenda.entities.Book
import com.example.open101.databinding.ItemEditBookBinding

class BookEditAdapter(private var listaEditBooks: ArrayList<Book>): RecyclerView.Adapter<BookEditAdapter.BookEditViewHolder>(), View.OnClickListener {


    private lateinit var listenerOnClickListener: View.OnClickListener

    class BookEditViewHolder(v: View): RecyclerView.ViewHolder(v) {


        val binding = ItemEditBookBinding.bind(v)
        fun bind (item: Book) {
            binding.tvNameBookList.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookEditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_edit_book, parent, false)
        view.setOnClickListener(this)
        return BookEditViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaEditBooks.size
    }

    override fun onBindViewHolder(holder: BookEditViewHolder, position: Int) {
        val item = listaEditBooks[position]
        holder.bind(item)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.listenerOnClickListener = listener
    }

    override fun onClick(v: View?) {
        listenerOnClickListener.onClick(v)
    }


}