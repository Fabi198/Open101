package com.example.open101.articlesList

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbArticlesHelper(var context: Context, var DB_NAME: String = "articulos", DB_VERSION: Int = 2):
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE $DB_NAME (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, DESCRIPCION TEXT NOT NULL, PRECIO_REAL INT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE $DB_NAME")
        onCreate(db)
    }
}