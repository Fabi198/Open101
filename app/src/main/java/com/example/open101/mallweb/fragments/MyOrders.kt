package com.example.open101.mallweb.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentMyOrdersBinding
import com.example.open101.mallweb.adapters.OrdersAdapter
import com.example.open101.mallweb.db.DbMallweb
import com.example.open101.mallweb.fragmentsDrawerMenu.ShoppingCartFragmentStep1


class MyOrders : Fragment(R.layout.fragment_my_orders) {

    private lateinit var binding: FragmentMyOrdersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyOrdersBinding.bind(view)
        val containerIDParent = arguments?.getInt("ContainerParentID")
        val abbandoned = arguments?.getBoolean(getString(R.string.are_abbandoned))

        val dbMallweb = DbMallweb(requireContext())
        val prefs: SharedPreferences = requireActivity().getSharedPreferences("MY PREF", AppCompatActivity.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        if (email != null && abbandoned != null && containerIDParent != null) {
            val client = dbMallweb.queryForClient(email)
            binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
            if (abbandoned) {
                binding.rvOrders.adapter = OrdersAdapter(dbMallweb.queryForOrders(client.id, getString(R.string.abandonado)), requireContext()) {
                    dbMallweb.putDetailsBackToCart(it, client.id)
                    dbMallweb.editStateOrder(it, getString(R.string.en_curso))
                    showFragment(ShoppingCartFragmentStep1(), containerIDParent, existingOrder = it, idClient = client.id)
                }
            } else {
                binding.rvOrders.adapter = OrdersAdapter(dbMallweb.queryForOrders(client.id, getString(R.string.completado)), requireContext()) {}
            }

        }
    }

    private fun showFragment(
        fragment: Fragment,
        id: Int? = null,
        name: String? = null,
        idCArray: ArrayList<Int>? = null,
        idBrand: Int? = null,
        idClient: Int? = null,
        idProduct: Int? = null,
        withShipping: Boolean? = null,
        postalCode: Int? = null,
        existingOrder: Int? = null
    ) {
        if (id != null) {
            val bundle = Bundle()
            bundle.putInt("ContainerID", id)
            if (name != null) { bundle.putString("NameCategory", name) }
            if (idCArray != null) { bundle.putIntegerArrayList("IDCategoryArray", idCArray) }
            if (idBrand != null) { bundle.putInt("IdBrand", idBrand) }
            if (idClient != null) { bundle.putInt("IdClient", idClient)}
            if (idProduct != null) { bundle.putInt("IDProduct", idProduct)}
            if (existingOrder != null) { bundle.putInt(getString(R.string.existingOrder), existingOrder) }
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