package com.example.open101.booksAgenda.menuFragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.open101.R
import com.example.open101.booksAgenda.adapters.ContactListAdapter
import com.example.open101.booksAgenda.dbHelpers.DbContact
import com.example.open101.databinding.FragmentReadUsersBinding


class ReadUsersFragment : Fragment(R.layout.fragment_read_users) {

    private lateinit var binding: FragmentReadUsersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReadUsersBinding.bind(view)
        val dbContact = DbContact(requireActivity())
        val adapter = ContactListAdapter(dbContact.showContacts())
        binding.rvContacts.layoutManager = LinearLayoutManager(activity)
        binding.rvContacts.adapter = adapter

        binding.btnOut.setOnClickListener {
            val intent = Intent()
            requireActivity().setResult(AppCompatActivity.RESULT_CANCELED, intent)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }


}