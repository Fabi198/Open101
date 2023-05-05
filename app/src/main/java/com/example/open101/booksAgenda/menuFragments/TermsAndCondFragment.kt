package com.example.open101.booksAgenda.menuFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.databinding.FragmentTermsAndCondBinding


class TermsAndCondFragment : Fragment(R.layout.fragment_terms_and_cond) {

    private lateinit var binding: FragmentTermsAndCondBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTermsAndCondBinding.bind(view)


        binding.btnOut.setOnClickListener {
            requireActivity().onBackPressed()
        }


    }

}