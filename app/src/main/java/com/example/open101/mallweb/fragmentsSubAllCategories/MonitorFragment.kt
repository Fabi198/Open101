package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentMonitorBinding
import com.example.open101.mallweb.fragments.FeaturedBrands


class MonitorFragment : Fragment(R.layout.fragment_monitor) {

    private lateinit var binding: FragmentMonitorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonitorBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnMonitorGamer.setOnClickListener { showFragment(id, 26, "MONITOR GAMER") }
        binding.btnMonitorLCDLED.setOnClickListener { showFragment(id, 27, "MONITOR LCD/LED") }
    }

    private fun showFragment(id: Int?, i: Int, name: String) {
        if (id != null) {
            val fragment = FeaturedBrands()
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