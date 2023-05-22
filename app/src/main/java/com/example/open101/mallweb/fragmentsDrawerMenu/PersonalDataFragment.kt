package com.example.open101.mallweb.fragmentsDrawerMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentPersonalDataBinding

class PersonalDataFragment : Fragment(R.layout.fragment_personal_data) {

    private lateinit var binding: FragmentPersonalDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPersonalDataBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }



}