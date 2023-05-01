package com.example.open101.booksAgenda.dbHelpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.open101.booksAgenda.entities.Book
import java.sql.SQLException

class DbBooks(context: Context) : DbBookHelper(context) {

    private fun setDatabase(): SQLiteDatabase {
        val db = DbBookHelper(context)
        return db.writableDatabase
    }

    fun insertBook(n: String, a: String, y: String, g: String, u: Int): Long {
        var id: Long = 0

        try {
            setDatabase()
            val v = ContentValues()
            v.put("name", n)
            v.put("author", a)
            v.put("year", y)
            v.put("gender", g)
            v.put("units", u)

            id = setDatabase().insert(TABLE_NAME, null, v)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return id
    }

    fun deleteBook(id: Int): Boolean {
        setDatabase()
        val correcto: Boolean = try {
            setDatabase().execSQL("DELETE FROM $TABLE_NAME WHERE id = $id")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
        return correcto
    }

    fun editBook(id: Int, n: String, a: String, y: String, g: String, u: Int): Boolean {
        setDatabase()
        return try {
            setDatabase().execSQL("UPDATE $TABLE_NAME SET name = '$n', author = '$a', year = '$y', gender = '$g', units = '$u' WHERE id = $id")
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            setDatabase().close()
        }
    }

    fun seeBook(id: Int): Book {
        setDatabase()
        val book = Book()
        val cursorBook: Cursor = setDatabase().rawQuery("SELECT * FROM $TABLE_NAME WHERE id = $id", null)
        if (cursorBook.moveToFirst()) {
            book.id = cursorBook.getInt(0)
            book.name = cursorBook.getString(1)
            book.author = cursorBook.getString(2)
            book.year = cursorBook.getString(3)
            book.gender = cursorBook.getString(4)
            book.units = cursorBook.getInt(5)
        }
        cursorBook.close()
        return book
    }

    fun showBooks(): ArrayList<Book> {
        setDatabase()
        val listaBooks = ArrayList<Book>()
        val cursorBooks: Cursor = setDatabase().rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursorBooks.moveToFirst()) {
            do {
                val book = Book()
                book.id = cursorBooks.getInt(0)
                book.name = cursorBooks.getString(1)
                book.author = cursorBooks.getString(2)
                book.year = cursorBooks.getString(3)
                book.gender = cursorBooks.getString(4)
                book.units = cursorBooks.getInt(5)
                listaBooks.add(book)
            } while (cursorBooks.moveToNext())
        }
        cursorBooks.close()
        setDatabase().close()
        return listaBooks
    }
}