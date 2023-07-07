package com.example.open101.mallweb.fragmentsDrawerMenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.databinding.FragmentFeaturedBinding
import com.example.open101.misc.DarkMode.isDarkModeEnabled
import com.squareup.picasso.Picasso

class FeaturedFragment : Fragment(R.layout.fragment_featured) {

    private lateinit var binding: FragmentFeaturedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeaturedBinding.bind(view)

        if (isDarkModeEnabled(requireContext())) { fillLightLogos() } else { fillDarkLogos() }

    }

    private fun fillDarkLogos() {
        Picasso.get().load(R.drawable.mallweb_brand_logo_amd).resize(800, 800).centerInside().into(binding.imageView1)
        Picasso.get().load(R.drawable.mallweb_brand_logo_elgato).resize(800, 800).centerInside().into(binding.imageView2)
        Picasso.get().load(R.drawable.mallweb_brand_logo_logitech).resize(800, 800).centerInside().into(binding.imageView3)
        Picasso.get().load(R.drawable.mallweb_brand_logo_corsair).resize(800, 800).centerInside().into(binding.imageView4)
        Picasso.get().load(R.drawable.mallweb_brand_logo_gigabyte).resize(800, 800).centerInside().into(binding.imageView5)
        Picasso.get().load(R.drawable.mallweb_brand_logo_patriot).resize(800, 800).centerInside().into(binding.imageView6)
        Picasso.get().load(R.drawable.mallweb_brand_logo_crucial).resize(800, 800).centerInside().into(binding.imageView7)
        Picasso.get().load(R.drawable.mallweb_brand_logo_hikvision).resize(800, 800).centerInside().into(binding.imageView8)
        Picasso.get().load(R.drawable.mallweb_brand_logo_seagate).resize(800, 800).centerInside().into(binding.imageView9)
        Picasso.get().load(R.drawable.mallweb_brand_logo_ecovision).resize(800, 800).centerInside().into(binding.imageView10)
        Picasso.get().load(R.drawable.mallweb_brand_logo_wd).resize(800, 800).centerInside().into(binding.imageView11)
        Picasso.get().load(R.drawable.mallweb_brand_logo_intel).resize(800, 800).centerInside().into(binding.imageView12)
    }

    private fun fillLightLogos() {
        Picasso.get().load(R.drawable.mallweb_brand_logo_amd_light).resize(800, 800).centerInside().into(binding.imageView1)
        Picasso.get().load(R.drawable.mallweb_brand_logo_elgato_light).resize(800, 800).centerInside().into(binding.imageView2)
        Picasso.get().load(R.drawable.mallweb_brand_logo_logitech).resize(800, 800).centerInside().into(binding.imageView3)
        Picasso.get().load(R.drawable.mallweb_brand_logo_corsair_light).resize(800, 800).centerInside().into(binding.imageView4)
        Picasso.get().load(R.drawable.mallweb_brand_logo_gigabyte).resize(800, 800).centerInside().into(binding.imageView5)
        Picasso.get().load(R.drawable.mallweb_brand_logo_patriot).resize(800, 800).centerInside().into(binding.imageView6)
        Picasso.get().load(R.drawable.mallweb_brand_logo_crucial_light).resize(800, 800).centerInside().into(binding.imageView7)
        Picasso.get().load(R.drawable.mallweb_brand_logo_hikvision).resize(800, 800).centerInside().into(binding.imageView8)
        Picasso.get().load(R.drawable.mallweb_brand_logo_seagate).resize(800, 800).centerInside().into(binding.imageView9)
        Picasso.get().load(R.drawable.mallweb_brand_logo_ecovision).resize(800, 800).centerInside().into(binding.imageView10)
        Picasso.get().load(R.drawable.mallweb_brand_logo_wd_light).resize(800, 800).centerInside().into(binding.imageView11)
        Picasso.get().load(R.drawable.mallweb_brand_logo_intel_light).resize(800, 800).centerInside().into(binding.imageView12)
    }



}






