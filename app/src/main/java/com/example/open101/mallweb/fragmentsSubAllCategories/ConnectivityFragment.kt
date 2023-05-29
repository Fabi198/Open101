package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentConnectivityBinding
import com.example.open101.mallweb.fragments.FeaturedBrands


class ConnectivityFragment : Fragment(R.layout.fragment_connectivity) {

    private lateinit var binding: FragmentConnectivityBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConnectivityBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnPlacasRed.setOnClickListener { showFragment(id, 19, "PLACAS DE RED") }
        binding.btnAdaptadoresUSB.setOnClickListener { showFragment(id, 18, "ADAPTADORES USB") }
        binding.btnRouters.setOnClickListener { showFragment(id, 20, "ROUTERS") }
        binding.btnSwitches.setOnClickListener { showFragment(id, 21, "SWITCHES") }

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