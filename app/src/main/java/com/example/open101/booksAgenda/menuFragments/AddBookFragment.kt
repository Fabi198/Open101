package com.example.open101.booksAgenda.menuFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.R
import com.example.open101.booksAgenda.dbHelpers.DbBooks
import com.example.open101.databinding.FragmentAddBookBinding


class AddBookFragment : Fragment(R.layout.fragment_add_book) {


    private lateinit var binding: FragmentAddBookBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBookBinding.bind(view)


        binding.btnAddBook.setOnClickListener {
            if (areEmpty()) {
                Toast.makeText(activity, "Debe completar todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val dbBooks = DbBooks(requireActivity().applicationContext)
                val id: Long = dbBooks.insertBook(binding.txtBookName.text.toString(),
                    binding.txtAuthor.text.toString(),  binding.txtYear.text.toString(),
                    binding.txtGender.text.toString(), Integer.parseInt(binding.txtUnits.text.toString()))
                if (id > 0) {
                    Toast.makeText(activity, "Libro agregado", Toast.LENGTH_SHORT).show()
                    limpiar()
                } else {
                    Toast.makeText(activity, "No se agrego ningun libro", Toast.LENGTH_SHORT).show()
                }
            }
            val intent = Intent()
            requireActivity().setResult(AppCompatActivity.RESULT_OK, intent)
            reiniciarActivity(requireActivity())
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }


        binding.btnOut.setOnClickListener {
            val intent = Intent()
            requireActivity().setResult(AppCompatActivity.RESULT_CANCELED, intent)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    private fun limpiar() {
        binding.txtBookName.setText("")
        binding.txtAuthor.setText("")
        binding.txtYear.setText("")
        binding.txtGender.setText("")
        binding.txtUnits.setText("")
    }

    private fun areEmpty(): Boolean {
        var correcto = false
        if (binding.txtBookName.text.toString().isEmpty() ||
            binding.txtAuthor.text.toString().isEmpty() ||
            binding.txtYear.text.toString().isEmpty() ||
            binding.txtGender.text.toString().isEmpty() ||
            binding.txtUnits.text.toString().isEmpty()) {
            correcto = true
        }
        return correcto
    }

    private fun reiniciarActivity(actividad: Activity) {
        val intent = Intent()
        intent.setClass(actividad, actividad.javaClass)
        actividad.startActivity(intent)
        actividad.finish()
    }
}