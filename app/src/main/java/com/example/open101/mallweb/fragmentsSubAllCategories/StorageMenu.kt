package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentStorageMenuBinding


class StorageMenu : Fragment(R.layout.fragment_storage_menu) {

    private lateinit var binding: FragmentStorageMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStorageMenuBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }


}