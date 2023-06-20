package com.example.open101.mallweb.fragmentsDrawerMenu


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentShoppingCartStep1Binding
import com.example.open101.mallweb.adapters.CartItemAdapter
import com.example.open101.mallweb.db.DbMallweb
import com.example.open101.mallweb.entities.dbEntities.ShopCartItem
import com.example.open101.mallweb.fragments.ProductDetailFragment


@Suppress("DEPRECATION")
class ShoppingCartFragmentStep1 : Fragment(R.layout.fragment_shopping_cart_step_1) {

    private lateinit var binding: FragmentShoppingCartStep1Binding
    private lateinit var adapter: CartItemAdapter
    private lateinit var listProductsItem: ArrayList<ShopCartItem>
    private lateinit var dbMallweb: DbMallweb
    private val dollar = "$"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartStep1Binding.bind(view)
        setupUI()
        setShippingCardView()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        val id = arguments?.getInt("ContainerID")
        val idClient = arguments?.getInt("IdClient")
        dbMallweb = DbMallweb(requireContext())
        if (idClient != null) {
            listProductsItem = dbMallweb.queryForProductsOnCart(idClient)
            if (listProductsItem.size > 0) {
                var i = 0.0
                listProductsItem.forEach { val price = dbMallweb.queryForProduct(it.idProduct).price * it.quantity; i += price }
                adapter = CartItemAdapter(listProductsItem, requireContext(), { itemRemover(it) }, { changeSubtotal(it) }, { showFragment(ProductDetailFragment(), id, idProduct = it) })
                binding.tvSubtotalDisplayed.text = "U${dollar}S $i"
                binding.tvShippingCostDisplayed.text = "U${dollar}S 0"
                binding.tvTotalDisplayed.text = "U${dollar}S $i"
                binding.rvItemsShopCart.layoutManager = LinearLayoutManager(requireContext())
                binding.rvItemsShopCart.adapter = adapter
                binding.btnTakeAway.setOnClickListener {
                    if (createOrder()) {
                        showFragment(ShoppingCartFragmentStep2(), id)
                    }
                }
            } else {
                allGoneCartItsEmpty()
            }
        }
    }

    private fun createOrder(): Boolean {
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun changeSubtotal(idClient: Int) {
        dbMallweb = DbMallweb(requireContext())
        val list: ArrayList<ShopCartItem> = dbMallweb.queryForProductsOnCart(idClient)
        var i = 0.0
        list.forEach {
            val price = dbMallweb.queryForProduct(it.idProduct).price * it.quantity
            i += price
        }
        binding.tvSubtotalDisplayed.text = "U${dollar}S $i"
        binding.tvTotalDisplayed.text = "U${dollar}S $i"
    }

    @SuppressLint("SetTextI18n")
    private fun setShippingCardView() {
        val idClient = arguments?.getInt("IdClient")
        if (idClient != null) { dbMallweb.queryForShippingAddress(idClient).postalCode.let { if (it != "") { binding.etPostalCode.setText(it) } } }
        binding.btnSetPostalCode.setOnClickListener {
            if (binding.etPostalCode.text.toString().isNotEmpty()) {
                val postalCode = Integer.parseInt(binding.etPostalCode.text.toString())
                binding.etPostalCode.text.clear()
                binding.tvShippingCostDisplayed.setTextColor(resources.getColor(R.color.greenLight))
                binding.tvShippingCostDisplayed.text = "¡Gratis!"
                binding.btnShopWithShipping.visibility = View.VISIBLE
                binding.btnShopWithShipping.setOnClickListener {
                    if (createOrder()) {
                        showFragment(ShoppingCartFragmentStep2(), id, postalCode = postalCode, withShipping = true)
                    }
                }
            }
        }
    }

    private fun allGoneCartItsEmpty() {
        binding.svMainShoppingCart.visibility = View.GONE
        binding.svAdviceShoppingCart.visibility = View.GONE
        binding.cvShippingCardView.visibility = View.GONE
        binding.tvEmptyCart.visibility = View.VISIBLE
    }

    private fun itemRemover(position: Int) {
        listProductsItem.removeAt(position)
        adapter.notifyItemRemoved(position)
        if (listProductsItem.isEmpty()) {
            allGoneCartItsEmpty()
        }
    }

    private fun showFragment(
        fragment: Fragment,
        id: Int? = null,
        name: String? = null,
        idCArray: java.util.ArrayList<Int>? = null,
        idBrand: Int? = null,
        idClient: Int? = null,
        idProduct: Int? = null,
        withShipping: Boolean? = null,
        postalCode: Int? = null
    ) {
        if (id != null) {
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            if (name != null) { bundle.putString("NameCategory", name) }
            if (idCArray != null) { bundle.putIntegerArrayList("IDCategoryArray", idCArray) }
            if (idBrand != null) { bundle.putInt("IdBrand", idBrand) }
            if (idClient != null) { bundle.putInt("IdClient", idClient)}
            if (idProduct != null) { bundle.putInt("IDProduct", idProduct)}
            if (withShipping == true && postalCode != null) { bundle.putInt("postalCode", postalCode); bundle.putBoolean("withShipping", withShipping)} else if (withShipping == false){ bundle.putBoolean("withShipping", withShipping) }
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