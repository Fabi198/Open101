package com.example.open101.booksAgenda.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R
import com.example.open101.booksAgenda.entities.BookContact
import com.example.open101.databinding.ItemContactoBinding

class ContactListAdapter(private var listContacts: ArrayList<BookContact>): RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {


    class ContactListViewHolder(v: View): RecyclerView.ViewHolder(v) {

        val binding = ItemContactoBinding.bind(v)

        fun bind(item: BookContact) {
            binding.tvNameList.text = item.nombre
            binding.tvEmailList.text = item.email
            binding.tvBirthList.text = item.birth
            binding.tvUserList.text = item.user
            binding.tvPasswordList.text = item.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        return ContactListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contacto, parent, false))
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val item = listContacts[position]
        holder.bind(item)
    }
}