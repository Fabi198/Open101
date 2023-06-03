package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentComponentsBinding
import com.example.open101.mallweb.fragments.CategoryFragment


class ComponentsFragment : Fragment(R.layout.fragment_components) {

    private lateinit var binding: FragmentComponentsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComponentsBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnCombosActualizacion.setOnClickListener { showFragment(id, 9, "COMBOS ACTUALIZACION PC") }
        binding.btnFuentesAlimentacion.setOnClickListener { showFragment(id, 10, "FUENTES ALIMENTACION") }
        binding.btnMemoriasPc.setOnClickListener { showFragment(id, 11, "MEMORIAS PARA PC") }
        binding.btnMemoriasNotebook.setOnClickListener { showFragment(id, 12, "MEMORIAS PARA NOTEBOOK") }
        binding.btnMicroAMD.setOnClickListener { showFragment(id, 13, "MICROPROCESADORES PARA AMD") }
        binding.btnMicroINTEL.setOnClickListener { showFragment(id, 14, "MICROPROCESADORES PARA INTEL") }
        binding.btnMotherAMD.setOnClickListener { showFragment(id, 15, "MOTHER PARA AMD") }
        binding.btnMotherINTEL.setOnClickListener { showFragment(id, 16, "MOTHER PARA INTEL") }
        binding.btnPlacaDeVideo.setOnClickListener { showFragment(id, 17, "PLACA DE VIDEO") }
        binding.btnUPS.setOnClickListener { showFragment(id, 0, "UPS") }
    }

    private fun showFragment(id: Int?, i: Int, name: String) {
        if (id != null) {
            val fragment = CategoryFragment()
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