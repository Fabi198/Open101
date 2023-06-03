package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentStorageMenuBinding
import com.example.open101.mallweb.fragments.CategoryFragment


class StorageMenu : Fragment(R.layout.fragment_storage_menu) {

    private lateinit var binding: FragmentStorageMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStorageMenuBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnDiscosSSD.setOnClickListener { showFragment(id, 3, "DISCOS SSD") }
        binding.btnDiscosExternos.setOnClickListener { showFragment(id, 1, "DISCOS EXTERNOS") }
        binding.btnDiscosSATA.setOnClickListener { showFragment(id, 2, "DISCOS SATA") }
        binding.btnPendrives.setOnClickListener { showFragment(id, 4, "PENDRIVES") }
        binding.btnMemoriasSD.setOnClickListener { showFragment(id, 0, "MEMORIAS SD") }

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