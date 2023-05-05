package com.example.open101.booksAgenda

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.R
import com.example.open101.activitys.BookLoginActivity
import com.example.open101.booksAgenda.adapters.BookAdapter
import com.example.open101.booksAgenda.dbHelpers.DbBooks
import com.example.open101.booksAgenda.menuFragments.AddBookFragment
import com.example.open101.booksAgenda.menuFragments.DeleteBookFragment
import com.example.open101.booksAgenda.menuFragments.EditBookFragment
import com.example.open101.booksAgenda.menuFragments.EditFieldsFragment
import com.example.open101.databinding.ActivityBookHomeBinding
import com.example.open101.fragments.*
import com.google.android.material.navigation.NavigationView


class BookHomeActivity: AppCompatActivity(), BookFragmentsManager {

    private lateinit var binding: ActivityBookHomeBinding
    private lateinit var dbBooks: DbBooks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        dbBooks = DbBooks(this)
        val adapter = BookAdapter(dbBooks.showBooks())
        binding.rvBooks.adapter = adapter
        setSupportActionBar(binding.toolbarBookHome)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            binding.dlBooks,
            binding.toolbarBookHome,
            R.string.abrir,
            R.string.cerrar
        )
        binding.dlBooks.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        binding.nvLateral.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_add_book -> {
                    supportFragmentManager.beginTransaction()
                        .add(binding.bookContainer.id, AddBookFragment(), "AddBookFragment")
                        .addToBackStack(null)
                        .commit()
                    binding.bookContainer.visibility = View.VISIBLE
                }
                R.id.item_edit_book -> {
                    supportFragmentManager.beginTransaction()
                        .add(binding.bookContainer.id, EditBookFragment(), "EditBookFragment")
                        .addToBackStack(null)
                        .commit()
                    binding.bookContainer.visibility = View.VISIBLE
                }
                R.id.item_delete_book -> {
                    supportFragmentManager.beginTransaction()
                        .add(binding.bookContainer.id, DeleteBookFragment(), "DeleteBookFragment")
                        .addToBackStack(null)
                        .commit()
                    binding.bookContainer.visibility = View.VISIBLE
                }
                R.id.item_close_session -> {
                    startActivity(Intent(this, BookLoginActivity::class.java))
                    finish()
                }
            }
            binding.dlBooks.closeDrawers()
            true
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount >= 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun editBookResponse() {
        supportFragmentManager.beginTransaction().replace(binding.bookContainer.id, EditFieldsFragment(), "EditFieldsFragment").addToBackStack(null).commit()
    }

    override fun deleteBookResponse() {
        supportFragmentManager.beginTransaction().replace(binding.bookContainer.id, DeleteBookFragment(), "DeleteBookFragment").addToBackStack(null).commit()
    }
}