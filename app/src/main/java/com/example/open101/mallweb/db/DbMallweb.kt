package com.example.open101.mallweb.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.open101.mallweb.entities.Brand
import com.example.open101.mallweb.entities.Category
import com.example.open101.mallweb.entities.Client
import com.example.open101.mallweb.entities.Product
import java.sql.SQLException

class DbMallweb(context: Context): DbMallwebHelper(context) {

    private fun setDatabase(): SQLiteDatabase {
        val db = DbMallwebHelper(context)
        return db.writableDatabase
    }

    fun queryBasic(s: String): ArrayList<Product> {
        val listProduct = ArrayList<Product>()
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM products WHERE name LIKE '%$s%'", null)
        if (cursor.moveToFirst()) {
            do {
                val product = Product()
                product.id = cursor.getInt(0)
                product.codFab = cursor.getString(1)
                product.name = cursor.getString(2)
                product.idCategory = cursor.getInt(3)
                product.idBrand = cursor.getInt(4)
                product.stock = cursor.getInt(5)
                product.price = cursor.getDouble(6)
                product.image = cursor.getInt(7)
                listProduct.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        setDatabase().close()
        return listProduct
    }

    fun queryForClient(email: String): Client {
        setDatabase()
        val client = Client()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM clients WHERE email = '$email'", null)
        if (cursor.moveToFirst()) {
            client.id = cursor.getInt(0)
            client.email = cursor.getString(1)
            client.name = cursor.getString(2)
            client.lastName = cursor.getString(3)
            client.birthday = cursor.getString(4)
            client.codArea = cursor.getString(5)
            client.numCelular = cursor.getString(6)
            client.dni = cursor.getString(7)
            client.cuit = cursor.getString(8)
            client.wantABill = cursor.getString(9)
        }
        cursor.close()
        setDatabase().close()
        return client
    }

    fun editClient(id: Int, n: String, l: String, b: String, c: String, nu: String, dni: String, cuit: String, w: String): Boolean {
        setDatabase()
        return try {
            setDatabase().execSQL("UPDATE clients SET name = '$n', lastname = '$l', birthday = '$b', codarea = '$c', numCelular = '$nu', dni = '$dni', cuit = '$cuit', wantABill = '$w' WHERE id = '$id'")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun createBasicClient(email: String, name: String, lastName: String, dni: String, cuit: String): Long {
        var id: Long = 0
        val wantABill = "No"

        try {
            setDatabase()
            val v = ContentValues()
            v.put("email", email)
            v.put("name", name)
            v.put("lastname", lastName)
            v.put("dni", dni)
            v.put("cuit", cuit)
            v.put("wantABill", wantABill)

            id = setDatabase().insert("clients", null, v)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return id
    }

    fun queryForCategoryCant(i: Int): ArrayList<Int> {
        val idBrandArray = ArrayList<Int>()
        setDatabase()
        val listProduct = ArrayList<Product>()
        val cursorProduct: Cursor = setDatabase().rawQuery("SELECT idBrand, idCategory FROM products WHERE idBrand = '$i' ORDER BY idCategory", null)
        if (cursorProduct.moveToFirst()) {
            do {
                val product = Product()
                product.idBrand = cursorProduct.getInt(0)
                product.idCategory = cursorProduct.getInt(1)
                listProduct.add(product)
            } while (cursorProduct.moveToNext())
        }
        cursorProduct.close()
        setDatabase().close()

        var test = 0
        listProduct.forEach {
            if (it.idCategory != test) {
                idBrandArray.add(it.idCategory)
                test = it.idCategory
            }
        }
        return idBrandArray
    }

    fun queryForProduct(i: Int): Product {
        val product = Product()
        setDatabase()
        val cursorProduct: Cursor = setDatabase().rawQuery("SELECT * FROM products WHERE idProduct = '$i'", null)
        if (cursorProduct.moveToFirst()) {
            product.id = cursorProduct.getInt(0)
            product.codFab = cursorProduct.getString(1)
            product.name = cursorProduct.getString(2)
            product.idCategory = cursorProduct.getInt(3)
            product.idBrand = cursorProduct.getInt(4)
            product.stock = cursorProduct.getInt(5)
            product.price = cursorProduct.getDouble(6)
            product.image = cursorProduct.getInt(7)
        }
        setDatabase().close()
        cursorProduct.close()
        return product
    }

    fun queryForBrandCant(i: Int): ArrayList<Int> {
        val idBrandArray = ArrayList<Int>()
        setDatabase()
        val listProduct = ArrayList<Product>()
        val cursorProduct: Cursor = setDatabase().rawQuery("SELECT idBrand, idCategory FROM products WHERE idCategory = $i ORDER BY idBrand", null)
        if (cursorProduct.moveToFirst()) {
            do {
                val product = Product()
                product.idBrand = cursorProduct.getInt(0)
                product.idCategory = cursorProduct.getInt(1)
                listProduct.add(product)
            } while (cursorProduct.moveToNext())
        }
        cursorProduct.close()
        setDatabase().close()

        var test = 0
        listProduct.forEach {
            if (it.idBrand != test) {
                idBrandArray.add(it.idBrand)
                test = it.idBrand
            }
        }
        return idBrandArray
    }

    fun queryForBrand(i: Int): Brand {
        setDatabase()
        val brand = Brand()
        val cursorBrand: Cursor = setDatabase().rawQuery("SELECT * FROM brands WHERE idBrand = '$i'", null)
        if (cursorBrand.moveToFirst()) {
            do {
                brand.id = cursorBrand.getInt(0)
                brand.name = cursorBrand.getString(1)
                brand.image = cursorBrand.getInt(2)
            } while (cursorBrand.moveToNext())
        }
        cursorBrand.close()
        setDatabase().close()
        return brand
    }

    fun queryForSubCategory(i: Int): Category {
        setDatabase()
        val category = Category()
        val cursorCategory: Cursor = setDatabase().rawQuery("SELECT * FROM category WHERE idCategory = $i", null)
        if (cursorCategory.moveToFirst()) {
            do {
                category.id = cursorCategory.getInt(0)
                category.name = cursorCategory.getString(1)
            } while (cursorCategory.moveToNext())
        }
        cursorCategory.close()
        setDatabase().close()
        return category
    }

    fun queryForSubCategoryProducts(i: Int): ArrayList<Product> {
            setDatabase()
            val listProduct = ArrayList<Product>()
            val cursorProduct: Cursor = setDatabase().rawQuery("SELECT * FROM products WHERE idCategory = $i ORDER BY RANDOM() LIMIT 0,10", null)
            if (cursorProduct.moveToFirst()) {
                do {
                    val product = Product()
                    product.id = cursorProduct.getInt(0)
                    product.codFab = cursorProduct.getString(1)
                    product.name = cursorProduct.getString(2)
                    product.idCategory = cursorProduct.getInt(3)
                    product.idBrand = cursorProduct.getInt(4)
                    product.stock = cursorProduct.getInt(5)
                    product.price = cursorProduct.getDouble(6)
                    product.image = cursorProduct.getInt(7)
                    listProduct.add(product)
                } while (cursorProduct.moveToNext())
            }
            cursorProduct.close()
            setDatabase().close()
            return listProduct
    }

    fun queryForProductsByBrandAndCategory(iB: Int, iC: Int): ArrayList<Product> {
        setDatabase()
        val listProduct = ArrayList<Product>()
        val cursorProduct: Cursor = setDatabase().rawQuery("SELECT * FROM products WHERE idCategory = '$iC' AND idBrand = '$iB'", null)
        if (cursorProduct.moveToFirst()) {
            do {
                val product = Product()
                product.id = cursorProduct.getInt(0)
                product.codFab = cursorProduct.getString(1)
                product.name = cursorProduct.getString(2)
                product.idCategory = cursorProduct.getInt(3)
                product.idBrand = cursorProduct.getInt(4)
                product.stock = cursorProduct.getInt(5)
                product.price = cursorProduct.getDouble(6)
                product.image = cursorProduct.getInt(7)
                listProduct.add(product)
            } while (cursorProduct.moveToNext())
        }
        cursorProduct.close()
        setDatabase().close()
        return listProduct
    }
}