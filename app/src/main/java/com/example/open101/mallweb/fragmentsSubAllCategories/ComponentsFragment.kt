package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentComponentsBinding


class ComponentsFragment : Fragment(R.layout.fragment_components) {

    private lateinit var binding: FragmentComponentsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComponentsBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}