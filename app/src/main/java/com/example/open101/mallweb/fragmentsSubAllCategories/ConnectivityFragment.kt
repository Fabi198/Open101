package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentConnectivityBinding
import com.example.open101.mallweb.fragments.SubCategoryFragment
import com.example.open101.mallweb.fragments.ShowFragment.showFragmentFromFragment


class ConnectivityFragment : Fragment(R.layout.fragment_connectivity) {

    private lateinit var binding: FragmentConnectivityBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConnectivityBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnPlacasRed.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 19, nameCategory = "PLACAS DE RED") }
        binding.btnAdaptadoresUSB.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 18, nameCategory = "ADAPTADORES USB") }
        binding.btnRouters.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 20, nameCategory = "ROUTERS") }
        binding.btnSwitches.setOnClickListener { showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = 21, nameCategory = "SWITCHES") }

    }

}