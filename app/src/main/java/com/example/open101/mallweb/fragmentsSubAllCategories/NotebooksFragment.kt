package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentNotebooksBinding
import com.example.open101.mallweb.fragments.CategoryFragment


class NotebooksFragment : Fragment(R.layout.fragment_notebooks) {

    private lateinit var binding: FragmentNotebooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotebooksBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnNotebooks.setOnClickListener { showFragment(id, 30, "NOTEBOOKS") }
        binding.btnRepuestos.setOnClickListener { showFragment(id, 31, "REPUESTOS") }
        binding.btnBaterias.setOnClickListener { showFragment(id, 29, "BATERIAS") }
        binding.btnBaseNotebooks.setOnClickListener { showFragment(id, 28, "BASE PARA NOTEBOOK") }


    }

    private fun showFragment(id: Int?, i: Int, name: String) {
        if (id != null) {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            bundle.putInt("IDCategory", i)
            bundle.putString("NameCategory", name)
            fragment.arguments = bundle
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.right_in,
                    R.anim.left_out,
                    R.anim.right_in,
                    R.anim.left_out)
                .replace(id, fragment, fragment.tag)
                .addToBackStack(fragment.tag)
                .commit()
        }
    }

}