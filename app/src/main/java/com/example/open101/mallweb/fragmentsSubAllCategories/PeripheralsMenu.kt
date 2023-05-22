package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentPeripheralsMenuBinding


class PeripheralsMenu : Fragment(R.layout.fragment_peripherals_menu) {

    private lateinit var binding: FragmentPeripheralsMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPeripheralsMenuBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}