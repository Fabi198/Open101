package com.example.open101.mallweb.fragmentsDrawerMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentPromosBinding


class PromosFragment : Fragment(R.layout.fragment_promos) {

    private lateinit var binding: FragmentPromosBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPromosBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }
}