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

class BrandsAdapter(
    private val listBrandsArray: ArrayList<Int>,
    private val idCategory: Int,
    private val context: Context
): RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>() {


    class BrandsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMallwebProductsRecyclerviewBinding.bind(view)

        fun bind(iB: Int, iC: Int, context: Context) {
            val dbMallweb = DbMallweb(context)
            val b = dbMallweb.queryForBrand(iB)
            val adapter = ProductAdapter(dbMallweb.queryForProductsByBrandAndCategory(iB, iC))
            binding.btnSeeAll.visibility = View.INVISIBLE
            binding.tvTitleBrand.text = b.name
            binding.rvBrand.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.rvBrand.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        return BrandsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mallweb_products_recyclerview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listBrandsArray.size
    }

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        holder.bind(listBrandsArray[position], idCategory, context)
    }
}