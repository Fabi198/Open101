package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.FragmentPrintBinding
import com.example.open101.mallweb.adapters.PrintersAdapter
import com.example.open101.mallweb.entities.Print


class PrintFragment : Fragment(R.layout.fragment_print) {

    private lateinit var binding: FragmentPrintBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrintBinding.bind(view)
        // val id = arguments?.getInt("ContainerID")
        setupPrintersRecyclers()
    }


    private fun setHPList(): Array<Print> {
        val hp1 = Print(R.drawable.mallweb_hp2375multifuncion, "HP DeskJet 2375")
        val hp2 = Print(R.drawable.mallweb_hp2775, "HP DeskJet 2775")
        val hp3 = Print(R.drawable.mallweb_hp530, "HP Color ADF 530")
        val hp4 = Print(R.drawable.mallweb_hp750, "HP Color ADF 750")
        val hp5 = Print(R.drawable.mallweb_hp315multifuncion, "HP Scanner 315")
        return arrayOf(hp1, hp2, hp3, hp4, hp5)
    }

    private fun setPantumList(): Array<Print> {
        val hp1 = Print(R.drawable.mallweb_pantum_m6559nw, "Pantum M6559NW")
        val hp2 = Print(R.drawable.mallweb_pantum_p2509w, "Pantum P2509W")
        val hp3 = Print(R.drawable.mallweb_pantum_p3010dw, "Pantum P3010DW")
        return arrayOf(hp1, hp2, hp3)
    }

    private fun setBrotherList(): Array<Print> {
        val hp1 = Print(R.drawable.mallweb_brother_ds640, "Brother DS640")
        return arrayOf(hp1)
    }

    private fun setEPSONList(): Array<Print> {
        val hp1 = Print(R.drawable.mallweb_epson_l3250, "EPSON L3250")
        return arrayOf(hp1)
    }

    private fun setupPrintersRecyclers() {
        binding.rvHP.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvHP.adapter = PrintersAdapter(setHPList())
        binding.rvBrother.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvBrother.adapter = PrintersAdapter(setBrotherList())
        binding.rvPantum.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvPantum.adapter = PrintersAdapter(setPantumList())
        binding.rvEPSON.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvEPSON.adapter = PrintersAdapter(setEPSONList())
    }

}