package com.example.open101.mallweb.fragmentsDrawerMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentAllCategoriesBinding
import com.example.open101.mallweb.fragmentsSubAllCategories.*


class AllCategoriesFragment: Fragment(R.layout.fragment_all_categories) {

    private lateinit var binding: FragmentAllCategoriesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllCategoriesBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.tvAlmacenamiento.setOnClickListener { showFragment(id, StorageMenu()) }
        binding.tvConectividad.setOnClickListener { showFragment(id, ConnectivityFragment()) }
        binding.tvComponentesParaPC.setOnClickListener { showFragment(id, ComponentsFragment()) }
        binding.tvPerifericos.setOnClickListener { showFragment(id, PeripheralsMenu()) }
        binding.tvAudioYVideo.setOnClickListener { showFragment(id, AudioAndVideoFragment()) }
        binding.tvImpresion.setOnClickListener { showFragment(id, PrintFragment()) }
        binding.tvNotebooks.setOnClickListener { showFragment(id, NotebooksFragment()) }
        binding.tvMonitores.setOnClickListener { showFragment(id, MonitorFragment()) }
        binding.tvZonaGamer.setOnClickListener { showFragment(id, GamerZoneFragment()) }
    }

    private fun showFragment(id: Int?, fragment: Fragment) {
        if (id != null) {
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
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