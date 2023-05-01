package com.example.open101.booksAgenda

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.R
import com.example.open101.booksAgenda.adapters.BookAdapter
import com.example.open101.booksAgenda.dbHelpers.DbBooks
import com.example.open101.booksAgenda.menuFragments.AddBookFragment
import com.example.open101.booksAgenda.menuFragments.DeleteBookFragment
import com.example.open101.booksAgenda.menuFragments.EditBookFragment
import com.example.open101.booksAgenda.menuFragments.EditFieldsFragment
import com.example.open101.databinding.ActivityBookHomeBinding
import com.example.open101.fragments.*


class BookHomeActivity: AppCompatActivity(), FragmentListener {

    private lateinit var binding: ActivityBookHomeBinding
    private lateinit var dbBooks: DbBooks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }


    private fun setupUI() {
        setSupportActionBar(binding.toolbarBookHome)
        supportActionBar?.title = null
        binding.toolbarBookHome.inflateMenu(R.menu.home)
        dbBooks = DbBooks(this)
        val adapter = BookAdapter(dbBooks.showBooks())
        binding.rvBooks.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(this).inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add_book -> {
                supportFragmentManager.beginTransaction().add(binding.bookContainer.id, AddBookFragment(), "AddBookFragment").commit()
                binding.bookContainer.visibility = View.VISIBLE
            }
            R.id.item_edit_book -> {
                supportFragmentManager.beginTransaction().add(binding.bookContainer.id, EditBookFragment(), "EditBookFragment").commit()
                binding.bookContainer.visibility = View.VISIBLE
            }
            R.id.item_delete_book -> {
                supportFragmentManager.beginTransaction().add(binding.bookContainer.id, DeleteBookFragment(), "DeleteBookFragment").commit()
                binding.bookContainer.visibility = View.VISIBLE
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addBookResponse() {

    }

    override fun editBookResponse() {
        supportFragmentManager.beginTransaction().replace(binding.bookContainer.id, EditFieldsFragment(), "EditFieldsFragment").commit()
    }

    override fun deleteBookResponse() {
        supportFragmentManager.beginTransaction().replace(binding.bookContainer.id, DeleteBookFragment(), "DeleteBookFragment").commit()
    }

    override fun onBackTerms() {

    }
}