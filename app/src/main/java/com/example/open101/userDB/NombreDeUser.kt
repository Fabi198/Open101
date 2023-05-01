package com.example.open101.userDB

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "names")

class NombreDeUser: java.io.Serializable {

    @DatabaseField(id = true)
    private var id: Int? = null

    @DatabaseField
    private var name: String? = null


    fun setName(nombre: String?) {
        this.name = nombre
    }

    fun getName() : String? {
        return name
    }

    fun getId() : Int? {
        return id
    }
}