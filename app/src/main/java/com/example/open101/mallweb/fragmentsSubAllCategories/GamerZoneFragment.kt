package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentGamerZoneBinding


class GamerZoneFragment : Fragment(R.layout.fragment_gamer_zone) {

    private lateinit var binding: FragmentGamerZoneBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGamerZoneBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}