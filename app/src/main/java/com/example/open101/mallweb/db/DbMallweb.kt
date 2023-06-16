package com.example.open101.mallweb.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.open101.mallweb.entities.dbEntities.*
import java.sql.SQLException

class DbMallweb(context: Context): DbMallwebHelper(context) {



    private fun setDatabase(): SQLiteDatabase {
        val db = DbMallwebHelper(context)
        return db.writableDatabase
    }

    fun getProvinceForSpinner(cp: String): ArrayList<String> {
        val listProvinces = ArrayList<String>()
        val listPreProvince = ArrayList<Int>()
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT id_province FROM city WHERE cp = '$cp' ORDER BY id_province", null)
        if (cursor.moveToFirst()) { do { val idProvince = cursor.getInt(0); listPreProvince.add(idProvince) } while (cursor.moveToNext()) }
        var i = 0
        val listFinal = ArrayList<Int>()
        listPreProvince.forEach { if (it > i) { i = it; listFinal.add(it) } }
        listFinal.forEach { val cursorP: Cursor = setDatabase().rawQuery("SELECT province_name FROM province WHERE id = '$it'", null); if (cursorP.moveToFirst()) { listProvinces.add(cursorP.getString(0)) }; cursorP.close() }
        cursor.close()
        setDatabase().close()
        return listProvinces
    }

    fun getCitysForSpinner(postalCode: String, province_name: String): ArrayList<String> {
        setDatabase()
        val idProvince = getProvinceByName(province_name)
        val list = ArrayList<String>()
        val cursor: Cursor = setDatabase().rawQuery("SELECT city_name FROM city WHERE cp = '${Integer.parseInt(postalCode)}' AND id_province = '${idProvince.id}'", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        setDatabase().close()
        return list
    }

    private fun getProvinceByName(province_name: String): Province {
        setDatabase()
        val province = Province()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM province WHERE province_name = '$province_name'", null)
        if (cursor.moveToFirst()) {
            province.id = cursor.getInt(0)
            province.name = cursor.getString(1)
        }
        setDatabase().close()
        cursor.close()
        return province
    }

    fun getProvinces(): ArrayList<Province> {
        val listProvince = ArrayList<Province>()
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM province", null)
        if (cursor.moveToFirst()) {
            do {
                val province = Province()
                province.id = cursor.getInt(0)
                province.name = cursor.getString(1)
                listProvince.add(province)
            }while (cursor.moveToNext())
        }
        cursor.close()
        setDatabase().close()
        return listProvince
    }

    fun getCity(cp: String): City {
        val city = City()
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM city WHERE cp = '$cp'", null)
        if (cursor.moveToFirst()) {
            city.id = cursor.getInt(0)
            city.name = cursor.getString(1)
            city.postalCode = cursor.getInt(2)
            city.idProvince = cursor.getInt(3)
        }
        cursor.close()
        setDatabase().close()
        return city
    }

    fun createShippingAddress(idClient: Int, s: String, n: String, f: String, d: String, pC: String, p: String, l: String): Long {
        var id: Long = 0
        setDatabase()

        try {
            val v = ContentValues()
            v.put("idClient", idClient)
            v.put("street", s)
            v.put("number", n)
            v.put("floor", f)
            v.put("door", d)
            v.put("postalCode", pC)
            v.put("province", p)
            v.put("locality", l)
            id = setDatabase().insert("address", null, v)
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            setDatabase().close()
        }
        return id
    }

    fun createBillAddress(idClient: Int, s: String, n: String, f: String, d: String, pC: String, p: String, l: String): Long {
        var id: Long = 0
        setDatabase()

        try {
            val v = ContentValues()
            v.put("idClient", idClient)
            v.put("street", s)
            v.put("number", n)
            v.put("floor", f)
            v.put("door", d)
            v.put("postalCode", pC)
            v.put("province", p)
            v.put("locality", l)
            id = setDatabase().insert("billAddress", null, v)
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            setDatabase().close()
        }
        return id
    }

    fun editShippingAddress(idClient: Int, s: String, n: String, f: String, d: String, pC: String, p: String, l: String): Boolean {
        setDatabase()
        return try {
            setDatabase().execSQL("UPDATE address SET street = '$s', number = '$n', floor = '$f', door = '$d', postalCode = '$pC', province = '$p', locality = '$l' WHERE idClient = '$idClient'")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun editBillAddress(idClient: Int, s: String, n: String, f: String, d: String, pC: String, p: String, l: String): Boolean {
        setDatabase()
        return try {
            setDatabase().execSQL("UPDATE billAddress SET street = '$s', number = '$n', floor = '$f', door = '$d', postalCode = '$pC', province = '$p', locality = '$l' WHERE idClient = '$idClient'")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun queryForShippingAddress(idClient: Int): Address {
        val address = Address()
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM address WHERE idClient = '$idClient'", null)
        if (cursor.moveToFirst()) {
            address.idClient = cursor.getInt(0)
            address.street = cursor.getString(1)
            address.number = cursor.getString(2)
            address.floor = cursor.getString(3)
            address.door = cursor.getString(4)
            address.postalCode = cursor.getString(5)
            address.province = cursor.getString(6)
            address.locality = cursor.getString(7)
        }
        setDatabase().close()
        cursor.close()
        return address
    }

    fun queryForBillAddress(idClient: Int): Address {
        val address = Address()
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM billAddress WHERE idClient = '$idClient'", null)
        if (cursor.moveToFirst()) {
            address.idClient = cursor.getInt(0)
            address.street = cursor.getString(1)
            address.number = cursor.getString(2)
            address.floor = cursor.getString(3)
            address.door = cursor.getString(4)
            address.postalCode = cursor.getString(5)
            address.province = cursor.getString(6)
            address.locality = cursor.getString(7)
        }
        setDatabase().close()
        cursor.close()
        return address
    }

    fun createOrder() {

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
            client.email = cursor.getString(1) ?: ""
            client.name = cursor.getString(2) ?: ""
            client.lastName = cursor.getString(3) ?: ""
            client.birthday = cursor.getString(4) ?: ""
            client.codArea = cursor.getString(5) ?: ""
            client.numCelular = cursor.getString(6) ?: ""
            client.dni = cursor.getString(7) ?: ""
            client.cuit = cursor.getString(8) ?: ""
            client.wantABill = cursor.getString(9) ?: ""
            client.ivaCondition = cursor.getString(10) ?: ""
        }
        cursor.close()
        setDatabase().close()
        return client
    }

    fun editWantABillClient(w: String, id: Int, ic: String ?= null): Boolean {
        setDatabase()
        return try {
            if (w.lowercase() == "si" && ic != null) {
                setDatabase().execSQL("UPDATE clients SET wantABill = '$w', ivaCondition = '$ic' WHERE id = '$id'")
            } else if (w.lowercase() == "no") {
                setDatabase().execSQL("UPDATE clients SET wantABill = '$w' WHERE id = '$id'")
            }
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun editClient(id: Int, n: String, l: String, b: String, c: String, nu: String, dni: String, cuit: String, w: String, i: String ?= null): Boolean {
        setDatabase()
        return try {
            if (i != null) {
                setDatabase().execSQL("UPDATE clients SET name = '$n', lastname = '$l', birthday = '$b', codarea = '$c', numCelular = '$nu', dni = '$dni', cuit = '$cuit', wantABill = '$w', ivaCondition = '$i' WHERE id = '$id'")
            } else {
                setDatabase().execSQL("UPDATE clients SET name = '$n', lastname = '$l', birthday = '$b', codarea = '$c', numCelular = '$nu', dni = '$dni', cuit = '$cuit', wantABill = '$w' WHERE id = '$id'")
            }
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun deleteProductFromShopCart(idClient: Int, idProduct: Int): Boolean {
        setDatabase()
        return try {
            setDatabase().execSQL("DELETE FROM shopcart WHERE idClient = '$idClient' AND idProduct = '$idProduct'")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun actQuantityShopCartOnProduct(quantity: Int, idProduct: Int, idClient: Int): Boolean {
        setDatabase()
        return try {
            setDatabase().execSQL("UPDATE shopcart SET quantity = '$quantity' WHERE idProduct = '$idProduct' AND idClient = '$idClient'")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun queryForProductsOnCart(idClient: Int): ArrayList<ShopCartItem> {
        val list = ArrayList<ShopCartItem>()
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM shopcart WHERE idClient = '$idClient'", null)
        if (cursor.moveToFirst()) {
            do {
                val shopCartItem = ShopCartItem()
                shopCartItem.idClient = cursor.getInt(0)
                shopCartItem.idProduct = cursor.getInt(1)
                shopCartItem.quantity = cursor.getInt(2)
                list.add(shopCartItem)
            } while (cursor.moveToNext())
        }
        setDatabase().close()
        cursor.close()
        return list
    }

    private fun alreadyExistsProduct(idClient: Int, idProduct: Int): Boolean {
        var result = false
        setDatabase()
        val cursor: Cursor = setDatabase().rawQuery("SELECT * FROM shopcart WHERE idClient = '$idClient' AND idProduct = '$idProduct'", null)
        if (cursor.moveToFirst()) {
            result = true
        }
        setDatabase().close()
        cursor.close()
        return result
    }

    fun addToShoppingCart(idClient: Int, idProduct: Int, quantity: Int): Boolean {
        var result = false
        setDatabase()
        if (!alreadyExistsProduct(idClient, idProduct)) {
            try {
                val v = ContentValues()
                v.put("idClient", idClient)
                v.put("idProduct", idProduct)
                v.put("quantity", quantity)
                setDatabase().insert("shopcart", null, v)
                result = true
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        } else {
            val cursorQuantity: Cursor = setDatabase().rawQuery("SELECT quantity FROM shopcart WHERE idClient = '$idClient' AND idProduct = '$idProduct'", null)
            if (cursorQuantity.moveToFirst()) {
                val quantityInit = cursorQuantity.getInt(0)
                val quantityFinal = quantity + quantityInit
                cursorQuantity.close()
                setDatabase().execSQL("UPDATE shopcart SET quantity = $quantityFinal WHERE idClient = '$idClient' AND idProduct = '$idProduct'")
                result = true
            }
        }
        setDatabase().close()
        return result
    }

    fun deleteShopCart() {
        setDatabase().execSQL("DELETE FROM shopcart")
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
            setDatabase().close()
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