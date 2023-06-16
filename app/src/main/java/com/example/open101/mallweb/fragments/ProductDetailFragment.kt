package com.example.open101.mallweb.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.R
import com.example.open101.databinding.FragmentProductDetailBinding
import com.example.open101.mallweb.auth.AuthFragment
import com.example.open101.mallweb.db.DbMallweb
import com.example.open101.mallweb.fragmentsDrawerMenu.ShoppingCartFragmentStep1
import com.squareup.picasso.Picasso
import java.util.ArrayList


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding: FragmentProductDetailBinding


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        val dbMallweb = DbMallweb(requireContext())
        val idProduct = arguments?.getInt("IDProduct")
        val id = arguments?.getInt("ContainerID")
        setupUI()
        setShoppingCardView()
        setShippingCalculator()

        if (idProduct != null) {
            val product = dbMallweb.queryForProduct(idProduct)
            val brand = dbMallweb.queryForBrand(product.idBrand)
            if (brand.image != 0) {
                Picasso.get().load(brand.image).fit().into(binding.ivBrand)
                binding.ivBrand.setOnClickListener {
                    showFragment(SubCategoryFragment(), id, brand.name, dbMallweb.queryForCategoryCant(brand.id), brand.id)
                }
            } else {
                binding.ivBrand.visibility = View.GONE
                binding.tvBrand.text = brand.name
                binding.tvBrand.visibility = View.VISIBLE
                binding.tvBrand.setOnClickListener {
                    showFragment(SubCategoryFragment(), id, brand.name, dbMallweb.queryForCategoryCant(brand.id), brand.id)
                }
            }
            if (product.image != 0) {
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

    private fun getUserID(): Int {
        val dbMallweb = DbMallweb(requireContext())
        var id = 0
        val prefs: SharedPreferences = requireActivity().getSharedPreferences("MY PREF", AppCompatActivity.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val client = email?.let { dbMallweb.queryForClient(it) }
        if (client != null) {
            id = client.id
        }
        return id
    }

    private fun setShippingCalculator() {
        binding.cvShippingCalculator.setOnClickListener {  }
        binding.svMainProductDetail.setOnClickListener { if (binding.cvShippingCalculator.visibility == View.VISIBLE) {binding.cvShippingCalculator.visibility = View.GONE} }
        binding.btnCalculateShipping.setOnClickListener {
            binding.cvShippingCalculator.visibility = View.VISIBLE
            binding.btnCalculatorShipping.setOnClickListener {
                if (binding.tvDisplayed4.text.toString().isNotEmpty()) {
                    binding.tvDisplayed4.text.clear()
                    binding.btnCalculatorShipping.visibility = View.GONE
                    binding.tvDisplayed4.visibility = View.GONE
                    binding.clDisplayedResult.visibility = View.VISIBLE
                    binding.btnContinue.visibility = View.VISIBLE
                    binding.btnContinue.setOnClickListener {
                        binding.clDisplayedResult.visibility = View.GONE
                        binding.btnContinue.visibility = View.GONE
                        binding.tvDisplayed4.visibility = View.VISIBLE
                        binding.btnCalculatorShipping.visibility = View.VISIBLE
                    }
                }
            }
        }
        binding.btnCloseCVShippingCalculator.setOnClickListener { binding.cvShippingCalculator.visibility = View.GONE }
        binding.btnCloseCVShippingCalculator2.setOnClickListener {
            binding.clDisplayedResult.visibility = View.GONE
            binding.btnContinue.visibility = View.GONE
            binding.tvDisplayed4.visibility = View.VISIBLE
            binding.btnCalculatorShipping.visibility = View.VISIBLE
        }
    }

    private fun setShoppingCardView() {
        binding.cvShoppingCardView.setOnClickListener {  }
        val dbMallweb = DbMallweb(requireContext())
        val idProduct = arguments?.getInt("IDProduct")
        val id = arguments?.getInt("ContainerID")
        binding.svMainProductDetail.setOnClickListener { if (binding.cvShoppingCardView.visibility == View.VISIBLE) {binding.cvShoppingCardView.visibility = View.GONE} }
        binding.btnShop.setOnClickListener {
            if (session()) {
                binding.pbBtnShop.visibility = View.VISIBLE
                if (getUserID() > 0 && idProduct != null && Integer.parseInt(binding.cantProductDetail.text.toString()) <= dbMallweb.queryForProduct(idProduct).stock) {
                    val result = dbMallweb.addToShoppingCart(getUserID(), idProduct, Integer.parseInt(binding.cantProductDetail.text.toString()))
                    if (result) {
                        binding.pbBtnShop.visibility = View.GONE
                        binding.cvShoppingCardView.visibility = View.VISIBLE
                        binding.btnKeepShopping.setOnClickListener { binding.cvShoppingCardView.visibility = View.GONE }
                        binding.btnGoToShoppingCartFragment.setOnClickListener { showFragment(ShoppingCartFragmentStep1(), id, idClient = getUserID()) }
                    } else {
                        binding.pbBtnShop.visibility = View.GONE
                    }
                } else {
                    showAlertErrorQuantity()
                    binding.pbBtnShop.visibility = View.GONE
                }
            } else {
                showFragment(AuthFragment(), id)
            }

        }
        binding.btnCloseCV.setOnClickListener { binding.cvShoppingCardView.visibility = View.GONE }
    }

    private fun session(): Boolean {
        val prefs: SharedPreferences = requireActivity().getSharedPreferences("MY PREF", AppCompatActivity.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        return email != null && provider != null
    }

    private fun showFragment(fragment: Fragment, id: Int ?= null, name: String ?= null, idCArray: ArrayList<Int>?= null, idBrand: Int ?= null, idClient: Int ?= null) {
        if (id != null) {
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            if (name != null) { bundle.putString("NameCategory", name) }
            if (idCArray != null) { bundle.putIntegerArrayList("IDCategoryArray", idCArray) }
            if (idBrand != null) { bundle.putInt("IdBrand", idBrand) }
            if (idClient != null) { bundle.putInt("IdClient", idClient)}
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

    private fun showAlertErrorQuantity() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Cantidad insuficiente, por favor disminuyala")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}