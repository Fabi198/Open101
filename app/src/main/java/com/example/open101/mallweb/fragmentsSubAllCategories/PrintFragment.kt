package com.example.open101.mallweb.fragmentsSubAllCategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentPrintBinding
import com.example.open101.mallweb.adapters.SubCategorysAdapter
import com.example.open101.mallweb.fragments.FeaturedBrands
import java.util.ArrayList


class PrintFragment : Fragment(R.layout.fragment_print) {

    private lateinit var binding: FragmentPrintBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrintBinding.bind(view)
        val id = arguments?.getInt("ContainerID")

        val idCArray = setArrayCategory(arrayOf(22, 23, 24, 25))

        binding.rvPrinters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPrinters.adapter = SubCategorysAdapter(idCArray, requireContext()) {
            showFragment(id, it)
        }

    }

    private fun showFragment(id: Int?, i: Int) {
        if (id != null) {
            val fragment = FeaturedBrands()
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            bundle.putInt("IDCategory", i)
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

    private fun setArrayCategory(i: Array<Int>): ArrayList<Int> {
        val idCArray = ArrayList<Int>()
        i.forEach { idCArray.add(it) }
        return idCArray
    }

}