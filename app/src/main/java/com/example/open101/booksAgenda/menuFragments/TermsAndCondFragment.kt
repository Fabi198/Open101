package com.example.open101.booksAgenda.menuFragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.databinding.FragmentTermsAndCondBinding
import com.example.open101.fragments.FragmentListener


class TermsAndCondFragment : Fragment(R.layout.fragment_terms_and_cond) {

    private lateinit var binding: FragmentTermsAndCondBinding
    private lateinit var listener: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTermsAndCondBinding.bind(view)


        binding.btnOut.setOnClickListener {
            listener.onBackTerms()
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }


    }

}