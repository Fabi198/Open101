package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentNotebooksBinding


class NotebooksFragment : Fragment(R.layout.fragment_notebooks) {

    private lateinit var binding: FragmentNotebooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotebooksBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
    }

}