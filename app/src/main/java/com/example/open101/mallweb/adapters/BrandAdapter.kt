package com.example.open101.mallweb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.databinding.ItemMallwebProductsRecyclerviewBinding
import com.example.open101.mallweb.db.DbMallweb

class BrandAdapter(
    private val listSubCategorys: ArrayList<Int>,
    private val idBrand: Int,
    private val context: Context,
    private val onClickItem: (Int) -> Unit
): RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {



    class BrandViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMallwebProductsRecyclerviewBinding.bind(view)

        fun bind(iC: Int, context: Context, idBrand: Int, onClickItem: (Int) -> Unit) {
            val dbMallweb = DbMallweb(context)
            val c = dbMallweb.queryForSubCategory(iC)
            val adapter = ProductAdapter(dbMallweb.queryForProductsByBrandAndCategory(idBrand, iC)) {onClickItem(it)}
            binding.tvTitleBrand.text = c.name
            binding.btnSeeAll.visibility = View.INVISIBLE
            binding.rvBrand.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.rvBrand.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_mallweb_products_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return listSubCategorys.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(listSubCategorys[position], context, idBrand, onClickItem)
    }
}