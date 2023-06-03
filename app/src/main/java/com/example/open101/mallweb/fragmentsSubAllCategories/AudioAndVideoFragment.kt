package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentAudioAndVideoBinding
import com.example.open101.mallweb.fragments.CategoryFragment


class AudioAndVideoFragment : Fragment(R.layout.fragment_audio_and_video) {

    private lateinit var binding: FragmentAudioAndVideoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAudioAndVideoBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        binding.btnBarraDeSonido.setOnClickListener { showFragment(id, 5, "BARRA DE SONIDO") }
        binding.btnParlantes.setOnClickListener { showFragment(id, 6, "PARLANTES") }
        binding.btnSoportes.setOnClickListener { showFragment(id, 7, "SOPORTES") }
        binding.btnTVLCDLED.setOnClickListener { showFragment(id, 8, "TV LCD/LED") }

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