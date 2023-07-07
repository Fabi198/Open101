package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentMonitorBinding
import com.example.open101.mallweb.fragments.SubCategoryFragment
import com.example.open101.mallweb.fragments.ShowFragment.showFragmentFromFragment


class MonitorFragment : Fragment(R.layout.fragment_monitor) {

    private lateinit var binding: FragmentMonitorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonitorBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnMonitorGamer.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 26, nameCategory = "MONITOR GAMER") }
        binding.btnMonitorLCDLED.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 27, nameCategory = "MONITOR LCD/LED") }
    }

}