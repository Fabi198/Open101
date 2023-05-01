package com.example.open101.booksAgenda.menuFragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.booksAgenda.adapters.BookEditAdapter
import com.example.open101.booksAgenda.dbHelpers.DbBooks
import com.example.open101.booksAgenda.entities.Book
import com.example.open101.databinding.FragmentDeleteBookBinding
import com.example.open101.fragments.FragmentListener


class DeleteBookFragment : Fragment(R.layout.fragment_delete_book) {

    private lateinit var binding: FragmentDeleteBookBinding
    private lateinit var listener: FragmentListener
    private lateinit var listaBooks: ArrayList<Book>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeleteBookBinding.bind(view)

        Toast.makeText(activity, "Presione el libro que desea borrar", Toast.LENGTH_SHORT).show()
        setupUI()
    }

    private fun setupUI() {
        var correcto: Boolean
        val dbBooks = DbBooks(requireActivity())
        val adapter = BookEditAdapter(dbBooks.showBooks())
        listaBooks = ArrayList()
        listaBooks = dbBooks.showBooks()

        binding.rvDeleteBooks.layoutManager = LinearLayoutManager(activity)
        binding.rvDeleteBooks.adapter = adapter
        adapter.setOnClickListener {
            val id2 = it
            binding.sureDeleteLayout.visibility = View.VISIBLE
            binding.btnYesDelete.setOnClickListener {
                correcto =
                    dbBooks.deleteBook(listaBooks[binding.rvDeleteBooks.getChildAdapterPosition(id2)].id)
                if (correcto) {
                    Toast.makeText(activity, "Se borro el libro", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "No se borro el libro", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent()
                requireActivity().setResult(AppCompatActivity.RESULT_OK, intent)
                reiniciarActivity(requireActivity())
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            }

            binding.btnNoDelete.setOnClickListener {
                val intent = Intent()
                requireActivity().setResult(AppCompatActivity.RESULT_CANCELED, intent)
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            }
        }

        binding.btnOut.setOnClickListener {
            val intent = Intent()
            requireActivity().setResult(AppCompatActivity.RESULT_CANCELED, intent)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            listener = context
        }
    }

    private fun reiniciarActivity(actividad: Activity) {
        val intent = Intent()
        intent.setClass(actividad, actividad.javaClass)
        actividad.startActivity(intent)
        actividad.finish()
    }
}