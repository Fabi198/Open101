package com.example.open101.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentButtonBinding

class ButtonFragment : Fragment(R.layout.fragment_button) {

    private lateinit var listener: FragmentNotiManager
    private lateinit var binding: FragmentButtonBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentNotiManager){
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentButtonBinding.bind(view)

        binding.btnShowContent.setOnClickListener {
            listener.showNoti()
        }
    }
}