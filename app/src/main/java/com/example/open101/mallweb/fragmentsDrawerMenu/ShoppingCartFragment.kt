package com.example.open101.mallweb.fragmentsDrawerMenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentShoppingCartBinding


class ShoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private lateinit var binding: FragmentShoppingCartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")


    }




}