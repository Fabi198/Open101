package com.example.open101.booksAgenda.dbHelpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.open101.booksAgenda.entities.BookContact
import java.sql.SQLException

@Suppress("unused")
class DbContact(override var context: Context): DbMainContactHelper(context) {

    private fun setDatabase(): SQLiteDatabase {
        val dbHelper = DbMainContactHelper(context)
        return dbHelper.writableDatabase
    }

    fun insertContact(n: String, e: String, u: String, b: String, p: String): Long {
        var id: Long = 0

        try {
            setDatabase()
            val v = ContentValues()
            v.put("name", n)
            v.put("email", e)
            v.put("user", u)
            v.put("birth", b)
            v.put("password", p)
            id = setDatabase().insert(TABLE_CONTACTOS, null, v)

        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return id
    }

    fun deleteContact(id: Int): Boolean {
        setDatabase()
        val correcto: Boolean = try {
            setDatabase().execSQL("DELETE FROM $TABLE_CONTACTOS WHERE id = $id")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
        return correcto
    }

    fun editContact(id: Int, n: String, e: String, u: String, b: String, p: String): Boolean {
        setDatabase()
        val correcto: Boolean = try {
            setDatabase().execSQL("UPDATE $TABLE_CONTACTOS SET nombre = '$n', email = '$e', user = '$u', birth = '$b', password = '$p' WHERE id = $id")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
        return correcto
    }

    fun showContacts(): ArrayList<BookContact> {
        setDatabase()
        val listContacts = ArrayList<BookContact>()
        val cursorContacts: Cursor = setDatabase().rawQuery("SELECT * FROM $TABLE_CONTACTOS", null)
        if (cursorContacts.moveToFirst()) {
            do {
                val contact = BookContact()
                contact.id = cursorContacts.getInt(0)
                contact.nombre = cursorContacts.getString(1)
                contact.email = cursorContacts.getString(2)
                contact.user = cursorContacts.getString(3)
                contact.birth = cursorContacts.getString(4)
                contact.password = cursorContacts.getString(5)
                listContacts.add(contact)
            } while (cursorContacts.moveToNext())
        }
        cursorContacts.close()
        setDatabase().close()
        return listContacts
    }

    fun seeContact(id: Int): BookContact {
        setDatabase()
        val contact = BookContact()
        val cursorContacts: Cursor = setDatabase().rawQuery("SELECT * FROM $TABLE_CONTACTOS WHERE id = '$id'", null)

        if (cursorContacts.moveToFirst()) {
                contact.id = cursorContacts.getInt(0)
                contact.nombre = cursorContacts.getString(1)
                contact.email = cursorContacts.getString(2)
                contact.user = cursorContacts.getString(3)
                contact.birth = cursorContacts.getString(4)
                contact.password = cursorContacts.getString(5)
        }

        cursorContacts.close()
        setDatabase().close()

        return contact
    }

    fun compareUserAndPassword(): ArrayList<BookContact> {
        setDatabase()
        val listaWithUP = ArrayList<BookContact>()
        val cursorContacts: Cursor = setDatabase().rawQuery("SELECT ID, USER, PASSWORD FROM $TABLE_CONTACTOS", null)

        if (cursorContacts.moveToFirst()){
            do {
                val contact = BookContact(id = cursorContacts.getInt(0),
                        user = cursorContacts.getString(1),
                        password = cursorContacts.getString(2))

                listaWithUP.add(contact)
            } while (cursorContacts.moveToNext())
        }

        cursorContacts.close()
        setDatabase().close()

        return listaWithUP
    }
}