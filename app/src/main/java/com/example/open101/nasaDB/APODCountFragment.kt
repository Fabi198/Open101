package com.example.open101.nasaDB

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentAPODCountBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class APODCountFragment : Fragment(R.layout.fragment_a_p_o_d_count) {
        private lateinit var binding: FragmentAPODCountBinding
        private lateinit var listener: BackListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAPODCountBinding.bind(view)
        val count = arguments?.getString("Count", "0")
        binding.rvAPOD.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitNASA.APINASA.getAPODCOUNTService("apod?api_key=1qGMihtHOX7hnyxaxP7WxcG0NuHNpf193UgKzRcE&count=$count")
            val apod = responseService.body()

            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful && apod != null && count!="0") {
                    val adapter = NASAAdapter(apod)
                    binding.rvAPOD.adapter = adapter
                    binding.pbAPOD.visibility = View.GONE
                    binding.rvAPOD.visibility = View.VISIBLE
                    binding.btnBack.visibility = View.VISIBLE

                    binding.btnBack.setOnClickListener {
                        listener.onBack()
                        back()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error en la base de datos", Toast.LENGTH_SHORT).show()
                    binding.pbAPOD.visibility = View.GONE
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BackListener){
            listener = context
        }
    }

    private fun back() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }


}