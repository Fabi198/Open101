package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentGamerZoneBinding
import com.example.open101.mallweb.fragments.FeaturedBrands


class GamerZoneFragment : Fragment(R.layout.fragment_gamer_zone) {

    private lateinit var binding: FragmentGamerZoneBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGamerZoneBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnAuricularGamer.setOnClickListener { showFragment(id, 41, "AURICULAR GAMER") }
        binding.btnComponentesStreaming.setOnClickListener { showFragment(id, 42, "COMPONENTES PARA STREAMING") }
        binding.btnGabinetesGamer.setOnClickListener { showFragment(id, 43, "GABINETES GAMER") }
        binding.btnMouseGamer.setOnClickListener { showFragment(id, 44, "MOUSE GAMER") }
        binding.btnPadMouseGamer.setOnClickListener { showFragment(id, 45, "PAD MOUSE GAMER") }
        binding.btnSillaGamer.setOnClickListener { showFragment(id, 46, "SILLA GAMER") }
        binding.btnVolantesGamer.setOnClickListener { showFragment(id, 47, "VOLANTES Y JOYSTICK GAMER") }

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