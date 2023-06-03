package com.example.open101.mallweb.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.open101.R
import com.example.open101.databinding.FragmentProductDetailBinding
import com.example.open101.mallweb.db.DbMallweb
import com.squareup.picasso.Picasso
import java.util.ArrayList


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding: FragmentProductDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        val id = arguments?.getInt("ContainerID")
        val idProduct = arguments?.getInt("IDProduct")
        val dbMallWeb = DbMallweb(requireContext())
        setupUI()


        if (idProduct != null) {
            val product = dbMallWeb.queryForProduct(idProduct)
            val brand = dbMallWeb.queryForBrand(product.idBrand)
            if (brand.image > 0) {
                Picasso.get().load(brand.image).fit().into(binding.ivBrand)
                binding.ivBrand.setOnClickListener {
                    showFragment(id, brand.name, dbMallWeb.queryForCategoryCant(brand.id), brand.id)
                }
            } else {
                binding.ivBrand.visibility = View.GONE
                binding.tvBrand.text = brand.name
                binding.tvBrand.visibility = View.VISIBLE
                binding.tvBrand.setOnClickListener {
                    showFragment(id, brand.name, dbMallWeb.queryForCategoryCant(brand.id), brand.id)
                }
            }
            if (product.image > 0) {
                Picasso.get().load(product.image).fit().into(binding.ivProductDetail)
            }
            binding.tvTitleProductDetail.text = product.name
            val dollar = "$"
            binding.tvPriceProductDetail.text = "U${dollar}S ${product.price}"
        }




    }

    private fun setupUI() {
        var favorite = false
        Picasso.get().load(R.drawable.mallweb_icon_favorite_empty).fit().into(binding.btnFavorites)
        binding.btnFavorites.setOnClickListener {
            favorite = if (!favorite) {
                Picasso.get().load(R.drawable.mallweb_icon_favorite_full).fit().into(binding.btnFavorites)
                true
            } else {
                Picasso.get().load(R.drawable.mallweb_icon_favorite_empty).fit().into(binding.btnFavorites)
                false
            }
        }
        Picasso.get().load(R.drawable.mallweb_icono_shared_facebook).fit().into(binding.btnSharedFacebook)
        binding.btnSharedFacebook.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.mallweb.com.ar"))) }
        Picasso.get().load(R.drawable.mallweb_icono_shared_whatsapp).fit().into(binding.btnSharedWhatsapp)
        binding.btnSharedWhatsapp.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?text=https://www.mallweb.com.ar"))) }
        Picasso.get().load(R.drawable.mallweb_icono_shared_twitter).fit().into(binding.btnSharedTwitter)
        binding.btnSharedTwitter.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?original_referer=https://www.mallweb.com.ar/discos-solidos/almacenamiento---disco-de-estado-solido/246927-disco-solido-ssd-240-gb-nvme-western-digital-green-sn350.html&tw_p=tweetbutton&url=https://www.mallweb.com.ar"))) }

    }

    private fun showFragment(id: Int ?= null, name: String ?= null, idCArray: ArrayList<Int>?= null, idBrand: Int ?= null) {
        if (id != null) {
            val fragment = SubCategoryFragment()
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            if (name != null) { bundle.putString("NameCategory", name) }
            if (idCArray != null) { bundle.putIntegerArrayList("IDCategoryArray", idCArray) }
            if (idBrand != null) { bundle.putInt("IdBrand", idBrand) }
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