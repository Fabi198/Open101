package com.example.open101.userDB

import android.content.Context
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.j256.ormlite.dao.Dao
import java.sql.SQLException

class NombresRepository (context: Context) {

    private lateinit var dao: Dao<NombreDeUser, Int>

    init {
        val helper = OpenHelperManager.getHelper(context, DBHelper::class.java)

        try {
            dao = helper.getDao(NombreDeUser::class.java)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun getNames(): List<NombreDeUser> {
        return dao.queryForAll()
    }

    fun addName(name: NombreDeUser) {
        dao.create(name)
    }

    fun deleteAll(id: Int) {
        dao.deleteById(id)
    }

}