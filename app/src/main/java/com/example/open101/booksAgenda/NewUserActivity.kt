package com.example.open101.booksAgenda

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.R
import com.example.open101.booksAgenda.dbHelpers.DbContact
import com.example.open101.booksAgenda.menuFragments.ReadUsersFragment
import com.example.open101.databinding.ActivityNewUserBinding

class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding
    private lateinit var txtName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtUser: EditText
    private lateinit var txtBirth: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtCPassword: EditText
    private lateinit var btnSetUser: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtName = binding.txtNombre
        txtEmail = binding.txtEmail
        txtUser = binding.txtUser
        txtBirth = binding.txtBirth
        txtPassword = binding.txtPassword
        txtCPassword = binding.txtCPassword
        btnSetUser = binding.btnSetUsuario
        setSupportActionBar(binding.toolbarNewUser)
        supportActionBar?.title = null
        binding.toolbarNewUser.inflateMenu(R.menu.list_user)

        btnSetUser.setOnClickListener {
            if (areEmpty()) {
                Toast.makeText(this, "DEBE COMPLETAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show()
            } else {
                if (txtPassword.text.toString() == txtCPassword.text.toString()) {
                    val dbContact = DbContact(this)
                    val id: Long = dbContact.insertContact(
                        txtName.text.toString(),
                        txtEmail.text.toString(),
                        txtUser.text.toString(),
                        txtBirth.text.toString(),
                        txtPassword.text.toString()
                    )

                    if (id > 0) {
                        Toast.makeText(this, "USUARIO CREADO", Toast.LENGTH_LONG).show()
                        limpiar()
                        finish()
                    } else {
                        Toast.makeText(this, "F EN EL CHAT", Toast.LENGTH_LONG).show()
                        limpiar()
                    }
                } else {
                    Toast.makeText(this, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_LONG).show()
                }
            }
            }
    }

    private fun areEmpty(): Boolean {
        var correcto = false
        if (txtName.text.toString().isEmpty() ||
            txtEmail.text.toString().isEmpty() ||
            txtUser.text.toString().isEmpty() ||
            txtBirth.text.toString().isEmpty() ||
            txtPassword.text.toString().isEmpty() ||
            txtCPassword.text.toString().isEmpty()) {
            correcto = true
        }
        return correcto
    }

    private fun limpiar() {
        txtName.setText("")
        txtEmail.setText("")
        txtUser.setText("")
        txtBirth.setText("")
        txtPassword.setText("")
        txtCPassword.setText("")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.list_user, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_list_person) {
            //val intent = Intent(this, ReadUsersActivity::class.java)
            //startActivity(intent)
            supportFragmentManager.beginTransaction().add(binding.usersContainer.id, ReadUsersFragment(), "ReadUsersFragment").commit()
            binding.usersContainer.visibility = View.VISIBLE
        }
        return super.onOptionsItemSelected(item)
    }
}