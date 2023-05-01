package com.example.open101.booksAgenda.menuFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.booksAgenda.dbHelpers.DbBooks
import com.example.open101.databinding.FragmentEditFieldsBinding


@Suppress("NAME_SHADOWING")
class EditFieldsFragment : Fragment(R.layout.fragment_edit_fields) {

    private lateinit var binding: FragmentEditFieldsBinding
    private var id2 = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditFieldsBinding.bind(view)
        parentFragmentManager.setFragmentResultListener("book", this) { _, result ->
            id2 = result.getInt("id")
            val dbBooks = DbBooks(requireActivity())
            val book = dbBooks.seeBook(id2)
            binding.txtBookName.setText(book.name)
            binding.txtAuthor.setText(book.author)
            binding.txtYear.setText(book.year)
            binding.txtGender.setText(book.gender)
            binding.txtUnits.setText(book.units.toString())

            binding.btnEditBook.setOnClickListener {
                if (areEmpty()) {
                    Toast.makeText(activity, "Debe completar todos los campos", Toast.LENGTH_LONG)
                        .show()
                } else {
                    val dbBooks = DbBooks(requireActivity().applicationContext)
                    val correcto = dbBooks.editBook(
                        id2,
                        binding.txtBookName.text.toString(),
                        binding.txtAuthor.text.toString(),
                        binding.txtYear.text.toString(),
                        binding.txtGender.text.toString(),
                        Integer.parseInt(binding.txtUnits.text.toString())
                    )
                    if (correcto) {
                        Toast.makeText(activity, "Libro editado", Toast.LENGTH_SHORT).show()
                        limpiar()
                    } else {
                        Toast.makeText(activity, "No se edito ningun libro", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                val intent = Intent()
                requireActivity().setResult(AppCompatActivity.RESULT_OK, intent)
                reiniciarActivity(requireActivity())
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            }
            Log.i("id", "Paso por aca, el valor es $id2")
        }




        binding.btnOut.setOnClickListener {
            val intent = Intent()
            requireActivity().setResult(AppCompatActivity.RESULT_CANCELED, intent)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
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

    private fun limpiar() {
        binding.txtBookName.setText("")
        binding.txtAuthor.setText("")
        binding.txtYear.setText("")
        binding.txtGender.setText("")
        binding.txtUnits.setText("")
    }


    private fun reiniciarActivity(actividad: Activity) {
        val intent = Intent()
        intent.setClass(actividad, actividad.javaClass)
        actividad.startActivity(intent)
        actividad.finish()
    }
}