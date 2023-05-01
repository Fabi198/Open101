package com.example.open101.booksAgenda.dbHelpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbMainContactHelper(open var context: Context, DB_NAME: String = "usuarios", var TABLE_CONTACTOS: String = "t_contactos", DB_VERSION: Int = 1): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_CONTACTOS (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, email TEXT NOT NULL, user TEXT NOT NULL, birth TEXT NOT NULL, password TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE $TABLE_CONTACTOS")
        if (db != null) {
            onCreate(db)
        }
    }
}