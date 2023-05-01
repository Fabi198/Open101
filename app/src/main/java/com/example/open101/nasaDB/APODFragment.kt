package com.example.open101.nasaDB

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.open101.R
import com.example.open101.databinding.FragmentAPODBinding
import com.example.open101.translator.TranslateService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APODFragment : Fragment(R.layout.fragment_a_p_o_d) {

    private lateinit var binding: FragmentAPODBinding
    private lateinit var listener: BackListener
    private val translator = TranslateService

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAPODBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val responseService = RetrofitNASA.APINASA.getAPODService("apod?api_key=1qGMihtHOX7hnyxaxP7WxcG0NuHNpf193UgKzRcE")
            val apod = responseService.body()

            withContext(Dispatchers.Main) {
                if (responseService.isSuccessful && apod != null) {
                    //Picasso.get().load(apod.image).into(binding.ivAPOD)
                    Picasso.get().load(apod.image).centerCrop(350).resize(650, 450).into(binding.ivAPOD)
                    binding.tvDate.text = "Date: ${apod.date}"
                    translator.englishSpanishTranslator.translate(apod.title).addOnSuccessListener {
                        binding.tvTitle.text = "Title: ${apod.title} ($it)"
                    }
                    binding.tvAuthor.text = "Author: ${apod.author}"
                    translator.englishSpanishTranslator.translate(apod.desc).addOnSuccessListener {
                        binding.tvDesc.text = it
                    }
                    binding.pbNASA.visibility = View.GONE
                    binding.ivAPOD.visibility = View.VISIBLE
                    binding.tvDate.visibility = View.VISIBLE
                    binding.tvTitle.visibility = View.VISIBLE
                    binding.tvAuthor.visibility = View.VISIBLE
                    binding.tvDesc.visibility = View.VISIBLE
                    binding.btnBack.visibility = View.VISIBLE

                    binding.btnBack.setOnClickListener {
                        listener.onBack()
                        back()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error en la base de datos", Toast.LENGTH_SHORT).show()
                    binding.pbNASA.visibility = View.GONE
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