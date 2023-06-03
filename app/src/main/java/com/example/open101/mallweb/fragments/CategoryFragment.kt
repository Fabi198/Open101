package com.example.open101.mallweb.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentFeaturedBrandsBinding
import com.example.open101.mallweb.adapters.SubCategoryAdapter
import com.example.open101.mallweb.adapters.ProductAdapter
import com.example.open101.mallweb.db.DbMallweb


class CategoryFragment : Fragment(R.layout.fragment_featured_brands) {

    private lateinit var binding: FragmentFeaturedBrandsBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeaturedBrandsBinding.bind(view)
        val id = arguments?.getInt("ContainerID")
        val idCategory = arguments?.getInt("IDCategory")
        val stringSearch = arguments?.getString("Search")
        val name = arguments?.getString("NameCategory")


        if (idCategory != null) {
            val dbMallweb = DbMallweb(requireContext())
            val idBrandArray = dbMallweb.queryForBrandCant(idCategory)
            val adapter = SubCategoryAdapter(idBrandArray, idCategory, requireContext()) {
                showProductFragment(id, it)
            }
            binding.rvBrands.layoutManager = LinearLayoutManager(requireContext())
            binding.rvBrands.adapter = adapter
        }

        if (stringSearch != null) {
            val dbMallweb = DbMallweb(requireContext())
            val adapter = ProductAdapter(dbMallweb.queryBasic(stringSearch.lowercase())) {
                showProductFragment(id, it)
            }
            binding.tvTitleBrand.text = "'$name'"
            binding.rvBrands.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.rvBrands.adapter = adapter
        }

    }

    private fun showProductFragment(id: Int?, i: Int) {
        if (id != null) {
            val fragment = ProductDetailFragment()
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            bundle.putInt("IDProduct", i)
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