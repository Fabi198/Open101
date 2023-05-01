package com.example.open101.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.databinding.FragmentTextBinding

class TextFragment : Fragment(R.layout.fragment_text) {

    private lateinit var binding: FragmentTextBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTextBinding.bind(view)
    }

}