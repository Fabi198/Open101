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

class SubCategorysAdapter(private val listSubCategorys: ArrayList<Int>, private val context: Context, private val onClickItem: (Int) -> Unit): RecyclerView.Adapter<SubCategorysAdapter.SubCategorysViewHolder>() {



    class SubCategorysViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMallwebProductsRecyclerviewBinding.bind(view)

        fun bind(i: Int, onClickItem: (Int) -> Unit, context: Context) {
            val dbMallweb = DbMallweb(context)
            val c = dbMallweb.queryForSubCategory(i)
            val adapter = ProductAdapter(dbMallweb.queryForSubCategoryProducts(c.id))
            binding.tvTitleBrand.text = c.name
            if (binding.btnSeeAll.visibility == View.VISIBLE) {
                binding.btnSeeAll.setOnClickListener { onClickItem(c.id) }
            }
            binding.rvBrand.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.rvBrand.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategorysViewHolder {
        return SubCategorysViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_mallweb_products_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return listSubCategorys.size
    }

    override fun onBindViewHolder(holder: SubCategorysViewHolder, position: Int) {
        holder.bind(listSubCategorys[position], onClickItem, context)
    }
}