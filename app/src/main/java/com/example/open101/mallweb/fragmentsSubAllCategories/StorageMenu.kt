package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentStorageMenuBinding
import com.example.open101.mallweb.fragments.SubCategoryFragment
import com.example.open101.mallweb.fragments.ShowFragment.showFragmentFromFragment


class StorageMenu : Fragment(R.layout.fragment_storage_menu) {

    private lateinit var binding: FragmentStorageMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStorageMenuBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnDiscosSSD.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 3, nameCategory = "DISCOS SSD") }
        binding.btnDiscosExternos.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 1, nameCategory = "DISCOS EXTERNOS") }
        binding.btnDiscosSATA.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 2, nameCategory = "DISCOS SATA") }
        binding.btnPendrives.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 4, nameCategory = "PENDRIVES") }
        binding.btnMemoriasSD.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 0, nameCategory = "MEMORIAS SD") }

    }


}