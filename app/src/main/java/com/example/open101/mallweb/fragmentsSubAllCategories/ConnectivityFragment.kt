package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentConnectivityBinding


class ConnectivityFragment : Fragment(R.layout.fragment_connectivity) {

    private lateinit var binding: FragmentConnectivityBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConnectivityBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}