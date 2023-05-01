package com.example.open101.superhero.entities

import com.google.gson.annotations.SerializedName

data class Connections (
    @SerializedName("group-affiliation") val groupaffiliation: String,
    val relatives: String
)
