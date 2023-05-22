package com.example.open101.mallweb.fragmentsDrawerMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentFeaturedBrandsBinding


class FeaturedBrands : Fragment(R.layout.fragment_featured_brands) {

    private lateinit var binding: FragmentFeaturedBrandsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeaturedBrandsBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}