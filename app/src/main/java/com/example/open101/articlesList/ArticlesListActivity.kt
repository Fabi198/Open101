package com.example.open101.articlesList

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.open101.R

class ArticlesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbarlista)
        toolbar.title = null

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ArticulosAdapter(showArticles())
        recyclerView.adapter = adapter
    }

    private fun showArticles(): ArrayList<Articulos> {
        val listArticles: ArrayList<Articulos> = ArrayList()
        val dbMainHelper = DbArticlesHelper(this)
        val db: SQLiteDatabase = dbMainHelper.writableDatabase
        val dBNAME = dbMainHelper.DB_NAME
        val cursorBooks: Cursor = db.rawQuery("SELECT * FROM $dBNAME", null)
        if (cursorBooks.moveToFirst()) {
            do {
                val article = Articulos()
                article.codigo = cursorBooks.getInt(0)
                article.descripcion = cursorBooks.getString(1)
                article.price = cursorBooks.getInt(2)
                listArticles.add(article)
            } while (cursorBooks.moveToNext())
        }
        cursorBooks.close()
        db.close()
        return listArticles
    }

}