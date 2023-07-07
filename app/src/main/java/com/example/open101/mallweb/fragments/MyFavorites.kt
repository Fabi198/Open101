package com.example.open101.mallweb.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentMyFavoritesBinding
import com.example.open101.mallweb.adapters.FavoritesAdapter
import com.example.open101.mallweb.db.DbMallweb
import com.example.open101.mallweb.fragments.ShowFragment.showFragmentFromFragment


class MyFavorites : Fragment(R.layout.fragment_my_favorites) {

    private lateinit var binding: FragmentMyFavoritesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyFavoritesBinding.bind(view)
        val containerIDParent = arguments?.getInt("ContainerParentID")

        val dbMallweb = DbMallweb(requireContext())
        val prefs: SharedPreferences = requireActivity().getSharedPreferences("MY PREF", AppCompatActivity.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        if (email != null && containerIDParent != null) {
            val client = dbMallweb.queryForClient(email)
            if (dbMallweb.queryForFavorites(client.id).size > 0) {
                binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
                binding.rvFavorites.adapter = FavoritesAdapter(arrayListOf(client.id), requireContext(), {
                    showFragmentFromFragment(requireActivity(), ProductDetailFragment(), "ProductDetailFragment", containerIDParent, idProduct = it)
                }, {
                    binding.rvFavorites.visibility = View.GONE
                    binding.tvNoFavorites.text = getString(R.string.no_tenes_productos_en_favoritos)
                    binding.tvNoFavorites.visibility = View.VISIBLE
                })
            } else {
                binding.rvFavorites.visibility = View.GONE
                binding.tvNoFavorites.text = getString(R.string.no_tenes_productos_en_favoritos)
                binding.tvNoFavorites.visibility = View.VISIBLE
            }

        }


    }

}