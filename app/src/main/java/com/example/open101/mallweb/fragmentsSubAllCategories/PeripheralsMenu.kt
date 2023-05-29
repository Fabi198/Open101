package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentPeripheralsMenuBinding
import com.example.open101.mallweb.fragments.FeaturedBrands


class PeripheralsMenu : Fragment(R.layout.fragment_peripherals_menu) {

    private lateinit var binding: FragmentPeripheralsMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPeripheralsMenuBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnAuriculares.setOnClickListener { showFragment(id, 32, "AURICULARES") }
        binding.btnAuricularesConMicrofono.setOnClickListener { showFragment(id, 33, "AURICULARES CON MICROFONO") }
        binding.btnCamaraWeb.setOnClickListener { showFragment(id, 34, "CAMARA WEB") }
        binding.btnMouse.setOnClickListener { showFragment(id, 35, "MOUSES") }
        binding.btnMouseOptico.setOnClickListener { showFragment(id, 36, "MOUSE OPTICO") }
        binding.btnTabletas.setOnClickListener { showFragment(id, 37, "TABLETAS DIGITALIZADORAS") }
        binding.btnTeclado.setOnClickListener { showFragment(id, 38, "TECLADOS") }
        binding.btnTecladoMouse.setOnClickListener { showFragment(id, 39, "TECLADO + MOUSE") }
        binding.btnTecladoInalambrico.setOnClickListener { showFragment(id, 40, "TECLADO INALAMBRICO") }

    }

    private fun showFragment(id: Int?, i: Int, name: String) {
        if (id != null) {
            val fragment = FeaturedBrands()
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            bundle.putInt("IDCategory", i)
            bundle.putString("NameCategory", name)
            fragment.arguments = bundle
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.right_in,
                    R.anim.left_out,
                    R.anim.right_in,
                    R.anim.left_out)
                .replace(id, fragment, fragment.tag)
                .addToBackStack(fragment.tag)
                .commit()
        }
    }

}