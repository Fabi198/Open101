package com.example.open101.booksAgenda.menuFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.booksAgenda.BookFragmentsManager
import com.example.open101.booksAgenda.adapters.BookEditAdapter
import com.example.open101.booksAgenda.dbHelpers.DbBooks
import com.example.open101.booksAgenda.entities.Book
import com.example.open101.databinding.FragmentEditBookBinding


class EditBookFragment : Fragment(R.layout.fragment_edit_book) {

    private lateinit var binding: FragmentEditBookBinding
    private lateinit var listaBooks: ArrayList<Book>
    private lateinit var listener: BookFragmentsManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditBookBinding.bind(view)

        Toast.makeText(activity, "Presione el libro que desea editar", Toast.LENGTH_SHORT).show()
        listaBooks = ArrayList()
        binding.rvListEditBooks.layoutManager = LinearLayoutManager(activity)
        val dbBooks = DbBooks(requireActivity())
        listaBooks = dbBooks.showBooks()
        val adapter = BookEditAdapter(listaBooks)
        binding.rvListEditBooks.adapter = adapter
        adapter.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", listaBooks[binding.rvListEditBooks.getChildAdapterPosition(it)].id)
            parentFragmentManager.setFragmentResult("book", bundle)
            listener.editBookResponse()
        }
        binding.btnOut.setOnClickListener {
            val intent = Intent()
            requireActivity().setResult(AppCompatActivity.RESULT_CANCELED, intent)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BookFragmentsManager){
            listener = context
        }
    }
}