package com.example.open101.mallweb.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentBrandsBinding
import com.example.open101.mallweb.adapters.BrandAdapter
import com.example.open101.mallweb.adapters.SubCategorysAdapter


class BrandsFragment : Fragment(R.layout.fragment_brands) {

    private lateinit var binding: FragmentBrandsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrandsBinding.bind(view)
        val id = arguments?.getInt("ContainerID")
        val name = arguments?.getString("NameCategory")
        val idCArray = arguments?.getIntegerArrayList("IDCategoryArray")
        val idBrand = arguments?.getInt("IdBrand")
        Log.i("id", idBrand.toString())


        if (name != null && idCArray != null) {
            binding.tvTitleCategory.text = name
            binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
            binding.rvCategory.adapter = SubCategorysAdapter(idCArray, requireContext()) {
                showFragment(id, it)
            }
        }

        if (name != null && idCArray != null && (idBrand != null && idBrand > 0)) {
            binding.tvTitleCategory.text = name
            binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
            binding.rvCategory.adapter = BrandAdapter(idCArray, idBrand, requireContext())
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

}