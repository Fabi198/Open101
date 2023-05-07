package com.example.open101.activitys


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.R
import com.example.open101.booksAgenda.BookHomeActivity
import com.example.open101.booksAgenda.NewUserActivity
import com.example.open101.booksAgenda.dbHelpers.DbContact
import com.example.open101.booksAgenda.entities.BookContact
import com.example.open101.booksAgenda.menuFragments.TermsAndCondFragment
import com.example.open101.databinding.ActivityBookLoginBinding


class BookLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookLoginBinding
    private  var listContacts = ArrayList<BookContact>()
    private var bookUserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarBookLogin)
        supportActionBar?.title = null
        binding.etUser.setText(getSavedUser())


        binding.btnVisiPass.setOnClickListener {
            if (binding.etPassword.inputType == 0x00000081) {
                binding.etPassword.inputType = 0x00000001
                binding.btnVisiPass.setImageResource(R.drawable.baseline_visibility_24)
            } else {
                binding.etPassword.inputType = 0x00000081
                binding.btnVisiPass.setImageResource(R.drawable.baseline_visibility_off_24)
            }
        }


        binding.btnSetUser.setOnClickListener {
            val intent = Intent(this@BookLoginActivity, NewUserActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            if (binding.etUser.text.toString().isEmpty() || binding.etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Debe ingresar usuario y contraseña", Toast.LENGTH_LONG).show()
            } else {
                val dbContacts = DbContact(this)
                listContacts = dbContacts.compareUserAndPassword()

                var contador = 0

                for (i in listContacts.indices) {
                    if (binding.etUser.text.toString() == listContacts[i].user && binding.etPassword.text.toString() == listContacts[i].password
                    ) {
                        contador++
                        if (binding.cbRemember.isChecked) {
                            saveUser(binding.etUser.text.toString())
                        }
                        val intent = Intent(this@BookLoginActivity, BookHomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                if (contador == 0) {
                    Toast.makeText(this, "No coinciden los datos", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.tvTerms.setOnClickListener {
            supportFragmentManager.beginTransaction().add(binding.termsContainer.id, TermsAndCondFragment(), "TermsAndCondFragment").addToBackStack(null).commit()
            binding.termsContainer.visibility = View.VISIBLE
            allGone()
        }
    }

    private fun getSavedUser(): String? {
        val prefs: SharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)
        return prefs.getString(bookUserName, "")
    }

    private fun saveUser(user: String) {
        val prefs: SharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE)
        prefs.edit().putString(bookUserName, user).apply()
    }

    private fun allGone() {
        binding.tvUser.visibility = View.GONE
        binding.etUser.visibility = View.GONE
        binding.tvPassword.visibility = View.GONE
        binding.llPassword.visibility = View.GONE
        binding.cbRemember.visibility = View.GONE
        binding.tvTerms.visibility = View.GONE
        binding.llButtons.visibility = View.GONE
    }

    private fun allVisible() {
            binding.tvUser.visibility = View.VISIBLE
            binding.etUser.visibility = View.VISIBLE
            binding.tvPassword.visibility = View.VISIBLE
            binding.llPassword.visibility = View.VISIBLE
            binding.cbRemember.visibility = View.VISIBLE
            binding.tvTerms.visibility = View.VISIBLE
            binding.llButtons.visibility = View.VISIBLE
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount >= 1) {
            supportFragmentManager.popBackStack()
            allVisible()
        } else {
            finish()
        }
    }


}