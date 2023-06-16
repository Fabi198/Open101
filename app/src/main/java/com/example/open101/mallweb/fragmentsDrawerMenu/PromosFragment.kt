package com.example.open101.mallweb.fragmentsDrawerMenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.open101.R
import com.example.open101.databinding.FragmentPromosBinding


class PromosFragment : Fragment(R.layout.fragment_promos) {

    private lateinit var binding: FragmentPromosBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPromosBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")

        /*
        val dbMallweb = DbMallweb(requireContext())
        binding.button.setOnClickListener {
            if (binding.textView4.text.toString().isNotEmpty()) {
                val province = dbMallweb.getAllProvincesNamesForSpinner(requireContext())
                province.forEach { Log.i("testing", it) }
                val city = dbMallweb.getCity(Integer.parseInt(binding.textView4.text.toString()), requireContext())
                city.forEach {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
                //binding.textView2.text = city[0].id.toString()
                //binding.textView3.text = city[0].name
                //binding.textView5.text = city[0].postalCode.toString()
                //binding.textView6.text = city[0].idProvince.toString()
            }
        }

         */


    }
}