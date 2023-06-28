package com.example.open101.mallweb.fragmentsDrawerMenu


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.databinding.FragmentShoppingCartStep1Binding
import com.example.open101.mallweb.adapters.CartItemAdapter
import com.example.open101.mallweb.db.DbMallweb
import com.example.open101.mallweb.db.OrderAlarmReceiver
import com.example.open101.mallweb.entities.dbEntities.ShopCartItem
import com.example.open101.mallweb.fragments.ProductDetailFragment
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class ShoppingCartFragmentStep1 : Fragment(R.layout.fragment_shopping_cart_step_1) {

    private lateinit var binding: FragmentShoppingCartStep1Binding
    private lateinit var adapter: CartItemAdapter
    private lateinit var listProductsItem: ArrayList<ShopCartItem>
    private lateinit var dbMallweb: DbMallweb
    private var total = 0.0
    private val dollar = "$"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingCartStep1Binding.bind(view)
        setupUI()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        val id = arguments?.getInt("ContainerID")
        val idClient = arguments?.getInt("IdClient")
        val existingOrder = arguments?.getInt(getString(R.string.existingOrder))
        Log.i("postal", existingOrder.toString())
        dbMallweb = DbMallweb(requireContext())
        if (idClient != null) {
            listProductsItem = dbMallweb.queryForProductsOnCart(idClient)
            if (listProductsItem.size > 0) {
                total = 0.0
                listProductsItem.forEach { val price = dbMallweb.queryForProduct(it.idProduct).price * it.quantity; total += price }
                adapter = CartItemAdapter(listProductsItem, requireContext(), { itemRemover(it) }, { changeSubtotal(it) }, { showFragment(ProductDetailFragment(), id, idProduct = it) })
                binding.tvSubtotalDisplayed.text = "U${dollar}S ${String.format("%.2f", total)}"
                binding.tvShippingCostDisplayed.text = "U${dollar}S 0"
                binding.tvTotalDisplayed.text = "U${dollar}S ${String.format("%.2f", total)}"
                binding.rvItemsShopCart.layoutManager = LinearLayoutManager(requireContext())
                binding.rvItemsShopCart.adapter = adapter
                binding.btnTakeAway.setOnClickListener {
                    if (existingOrder != null) {
                        if (existingOrder > 0) {
                            showFragment(ShoppingCartFragmentStep2(), id, existingOrder = existingOrder)
                        } else if (existingOrder == 0) {
                            if (createOrder(idClient, total, shipping = "no", dbMallweb.queryForProductsOnCart(idClient)) > 0) {
                                Log.i("postalGetLastOrder", dbMallweb.queryForLastOrder(idClient).numOrder.toString())
                                setAlarm(dbMallweb.queryForLastOrder(idClient).numOrder)
                                showFragment(ShoppingCartFragmentStep2(), id)
                            }
                        }
                    } else {
                        if (createOrder(idClient, total, shipping = "no", dbMallweb.queryForProductsOnCart(idClient)) > 0) {
                            setAlarm(dbMallweb.queryForLastOrder(idClient).numOrder)
                            showFragment(ShoppingCartFragmentStep2(), id)
                        }
                    }
                }
                setShippingCardView(total, dbMallweb.queryForProductsOnCart(idClient))
            } else {
                allGoneCartItsEmpty()
            }
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun setAlarm(numOrder: Int) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), OrderAlarmReceiver::class.java)
        intent.putExtra(getString(R.string.numOrder), numOrder)
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.HOUR_OF_DAY, 24)

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun createOrder(idClient: Int, total: Double, shipping: String, listProducts: ArrayList<ShopCartItem>): Long {
        dbMallweb = DbMallweb(requireContext())
        return dbMallweb.createOrder(idClient, getDate(), total, "En curso", shipping, listProducts)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String {
        val cal: Calendar = GregorianCalendar()
        val date: Date = cal.time
        val df = SimpleDateFormat("yyyy-MM-dd")
        return df.format(date)
    }

    @SuppressLint("SetTextI18n")
    private fun changeSubtotal(idClient: Int) {
        dbMallweb = DbMallweb(requireContext())
        val list: ArrayList<ShopCartItem> = dbMallweb.queryForProductsOnCart(idClient)
        total = 0.0
        list.forEach {
            val price = dbMallweb.queryForProduct(it.idProduct).price * it.quantity
            total += price
        }
        binding.tvSubtotalDisplayed.text = "U${dollar}S ${String.format("%.2f", total)}"
        binding.tvTotalDisplayed.text = "U${dollar}S ${String.format("%.2f", total)}"
    }

    @SuppressLint("SetTextI18n")
    private fun setShippingCardView(total: Double, listProductsItem: ArrayList<ShopCartItem>) {
        val id = arguments?.getInt("ContainerID")
        val idClient = arguments?.getInt("IdClient")
        val existingOrder = arguments?.getInt("existingOrder")
        if (idClient != null) {
            dbMallweb.queryForShippingAddress(idClient).postalCode.let { if (it != "") { binding.etPostalCode.setText(it) } }
            binding.btnSetPostalCode.setOnClickListener {
                hideKeyboard()
                if (binding.etPostalCode.text.toString().isNotEmpty()) {
                    val postalCode = Integer.parseInt(binding.etPostalCode.text.toString())
                    binding.etPostalCode.text.clear()
                    binding.tvShippingCostDisplayed.setTextColor(resources.getColor(R.color.greenLight))
                    binding.tvShippingCostDisplayed.text = "¡Gratis!"
                    binding.btnShopWithShipping.visibility = View.VISIBLE
                    binding.btnShopWithShipping.setOnClickListener {
                        if (existingOrder != null) {
                            if (existingOrder > 0) {
                                showFragment(ShoppingCartFragmentStep2(), id, postalCode = postalCode, withShipping = true, existingOrder = existingOrder)
                            } else if (existingOrder == 0) {
                                if (createOrder(idClient, total, shipping = "si", listProductsItem) > 0) {
                                    showFragment(ShoppingCartFragmentStep2(), id, postalCode = postalCode, withShipping = true)
                                }
                            }
                        } else {
                            if (createOrder(idClient, total, shipping = "si", listProductsItem) > 0) {
                                showFragment(ShoppingCartFragmentStep2(), id, postalCode = postalCode, withShipping = true)
                            }
                        }
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

    private fun hideKeyboard() { val imm = requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager; imm.hideSoftInputFromWindow(binding.svSCFStep1.windowToken, 0) }

}