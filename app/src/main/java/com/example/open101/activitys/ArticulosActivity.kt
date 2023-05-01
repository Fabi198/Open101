package com.example.open101.activitys

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.open101.articlesList.ArticlesListActivity
import com.example.open101.articlesList.DbArticlesHelper
import com.example.open101.databinding.ActivityArticulosBinding

class ArticulosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticulosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticulosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSeeList.setOnClickListener {
            val intent = Intent(this@ArticulosActivity, ArticlesListActivity::class.java)
            startActivity(intent)
        }

        binding.btnUp.setOnClickListener {
            alta()
        }

        binding.btnDown.setOnClickListener {
            if (binding.etCode.visibility == View.INVISIBLE) {
                binding.etCode.visibility = View.VISIBLE
            } else {
                bajaporcodigo()
                binding.etCode.visibility = View.INVISIBLE
            }
        }

        binding.btnMod.setOnClickListener {
            modificacion()
        }

        binding.btnDescQuery.setOnClickListener {
            consultapordescripcion()
        }

        binding.btnCodeQuery.setOnClickListener {
            if (binding.etCode.visibility == View.INVISIBLE) {
                binding.etCode.visibility = View.VISIBLE
            } else {
                consultaporcodigo()
                binding.etCode.visibility = View.INVISIBLE
            }
        }
    }

    private fun alta() {
        val dbMainHelper = DbArticlesHelper(this)
        val db: SQLiteDatabase = dbMainHelper.writableDatabase
        val desc = binding.etDesc.text.toString()
        val price = binding.etPrice.text.toString()
        if (desc.isNotEmpty() && price.isNotEmpty()) {
            val values = ContentValues()
            values.put("descripcion", desc)
            values.put("precio_real", price)
            db.insert(dbMainHelper.databaseName, null, values)
            db.close()
            Toast.makeText(this, "Se cargaron los datos del articulo", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
        binding.etPrice.setText("")
        binding.etCode.setText("")
        binding.etDesc.setText("")
    }

    private fun consultaporcodigo() {
        val cod = binding.etCode.text.toString()
        if (cod.isEmpty()) {
            Toast.makeText(this, "Ingrese un codigo", Toast.LENGTH_SHORT).show()
        } else {
            val dbMainHelper = DbArticlesHelper(this)
            val db: SQLiteDatabase = dbMainHelper.writableDatabase
            val dbName = dbMainHelper.DB_NAME
            val fila = db.rawQuery(
                "SELECT DESCRIPCION, PRECIO_REAL FROM $dbName WHERE CODIGO = $cod",
                null
            )
            if (fila.moveToFirst()) {
                binding.etDesc.setText(fila.getString(0))
                binding.etPrice.setText(fila.getString(1))
            } else {
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT)
                    .show()
            }
            fila.close()
            db.close()
        }
    }

    private fun consultapordescripcion() {
        val desc = binding.etDesc.text.toString()
        if (desc.isEmpty()) {
            Toast.makeText(this, "Ingrese una descripcion", Toast.LENGTH_SHORT).show()
        } else {
            val dbMainHelper = DbArticlesHelper(this)
            val db: SQLiteDatabase = dbMainHelper.writableDatabase
            val dbName = dbMainHelper.DB_NAME
            val fila = db.rawQuery(
                "SELECT CODIGO, PRECIO_REAL FROM $dbName WHERE DESCRIPCION = '$desc'",
                null
            )
            if (fila.moveToFirst()) {
                binding.etCode.setText(fila.getString(0))
                binding.etPrice.setText(fila.getString(1))
            } else {
                Toast.makeText(
                    this,
                    "No existe un articulo con dicha descripcion",
                    Toast.LENGTH_SHORT
                ).show()
            }
            fila.close()
            db.close()
        }
    }

    private fun bajaporcodigo() {
        val cod = binding.etCode.text.toString()
        if (cod.isEmpty()) {
            Toast.makeText(this, "Ingrese un codigo", Toast.LENGTH_SHORT).show()
        } else {
            val dbMainHelper = DbArticlesHelper(this)
            val db: SQLiteDatabase = dbMainHelper.writableDatabase
            val cant = db.delete(dbMainHelper.databaseName, "CODIGO=$cod", null)
            db.close()
            binding.etPrice.setText("")
            binding.etCode.setText("")
            binding.etDesc.setText("")
            if (cant == 1) {
                Toast.makeText(this, "Se borro el articulo", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun modificacion() {
        val dbMainHelper = DbArticlesHelper(this)
        val db: SQLiteDatabase = dbMainHelper.writableDatabase
        val cod = binding.etCode.text.toString()
        val desc = binding.etDesc.text.toString()
        val pre = binding.etPrice.text.toString()
        if (cod.isNotEmpty() && desc.isNotEmpty() && pre.isNotEmpty()) {
            val values = ContentValues()
            values.put("CODIGO", cod)
            values.put("DESCRIPCION", desc)
            values.put("PRECIO_REAL", pre)
            val cant = db.update(
                dbMainHelper.databaseName, values,
                "WHERE CODIGO=$cod", null
            )
            db.close()
            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.etPrice.setText("")
            binding.etCode.setText("")
            binding.etDesc.setText("")
        } else {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
    }
}