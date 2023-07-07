package com.example.open101.mallweb.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentCategoryBinding
import com.example.open101.mallweb.adapters.BrandAdapter
import com.example.open101.mallweb.adapters.CategoryAdapterShow10ProductsForEachSubCategory
import com.example.open101.mallweb.db.DbMallweb
import com.example.open101.mallweb.fragments.ShowFragment.showFragmentFromFragment
import com.example.open101.mallweb.objects.Session.getUserID
import com.example.open101.mallweb.objects.Session.sessionFromFragment
import com.example.open101.misc.DarkMode.isDarkModeEnabled
import com.squareup.picasso.Picasso


class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryBinding.bind(view)
        val id = arguments?.getInt("ContainerID")
        val name = arguments?.getString("NameCategory")
        val idCArray = arguments?.getIntegerArrayList("IDCategoryArray")
        val idBrand = arguments?.getInt("IdBrand")

        if (id != null) {
            if (name != null && idCArray != null && (idBrand != null && idBrand > 0)) {
                val dbMallweb = DbMallweb(requireContext())
                if (isDarkModeEnabled(requireContext())) {
                    if (dbMallweb.queryForBrand(idBrand).imageLight > 0) {
                        Picasso.get().load(dbMallweb.queryForBrand(idBrand).imageLight).fit().centerInside().into(binding.ivBrand)
                        binding.ivBrand.visibility = View.VISIBLE
                    } else {
                        if (dbMallweb.queryForBrand(idBrand).image > 0) {
                            Picasso.get().load(dbMallweb.queryForBrand(idBrand).image).fit().centerInside().into(binding.ivBrand)
                            binding.ivBrand.visibility = View.VISIBLE
                        } else {
                            binding.tvTitleCategory.text = name
                        }
                    }
                } else {
                    if (dbMallweb.queryForBrand(idBrand).image > 0) {
                        Picasso.get().load(dbMallweb.queryForBrand(idBrand).image).fit().centerInside().into(binding.ivBrand)
                        binding.ivBrand.visibility = View.VISIBLE
                    } else {
                        binding.tvTitleCategory.text = name
                    }
                }
                binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
                val adapter: BrandAdapter = if (sessionFromFragment(requireActivity())) {
                    BrandAdapter(idCArray, idBrand, requireContext(), getUserID(requireContext(), requireActivity()), {
                        showFragmentFromFragment(requireActivity(), ProductDetailFragment(), "ProductDetailFragment", id, idProduct = it)
                    }, {
                        showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = it)
                    })
                } else {
                    BrandAdapter(idCArray, idBrand, requireContext(), onClickItem = {
                        showFragmentFromFragment(requireActivity(), ProductDetailFragment(), "ProductDetailFragment", id, idProduct = it)
                    }, onTitleBrandClick = {
                        showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = it)
                    })
                }
                binding.rvCategory.adapter = adapter
            } else if (name != null && idCArray != null) {
                val adapter: CategoryAdapterShow10ProductsForEachSubCategory = if (sessionFromFragment(requireActivity())) {
                    CategoryAdapterShow10ProductsForEachSubCategory(idCArray, requireContext(), getUserID(requireContext(), requireActivity()), {
                        showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = it)
                    }, {
                        showFragmentFromFragment(requireActivity(), ProductDetailFragment(), "ProductDetailFragment", id, idProduct = it)
                    }, {
                        showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = it)
                    })
                } else {
                    CategoryAdapterShow10ProductsForEachSubCategory(idCArray, requireContext(), onClickItem = {
                        showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = it)
                    }, onProductClicked = {
                        showFragmentFromFragment(requireActivity(), ProductDetailFragment(), "ProductDetailFragment", id, idProduct = it)
                    }, onTitleBrandClick = {
                        showFragmentFromFragment(requireActivity(), SubCategoryFragment(), "SubCategoryFragment", id, idCategory = it)
                    })
                }
                binding.tvTitleCategory.text = name
                binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCategory.adapter = adapter
            }
        }




    }

}