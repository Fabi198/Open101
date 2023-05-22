package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentMonitorBinding


class MonitorFragment : Fragment(R.layout.fragment_monitor) {

    private lateinit var binding: FragmentMonitorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonitorBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}