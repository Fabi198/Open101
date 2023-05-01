package com.example.open101.superhero.entities

import com.google.gson.annotations.SerializedName

data class Biography (
    @SerializedName("full-name") val fullname: String,
    @SerializedName("alter-egos") val alteregos: String,
    val aliases: List<String>,
    @SerializedName("place-of-birth") val placeofbirth: String,
    @SerializedName("first-appearance") val firstappearance: String,
    val publisher: String,
    val alignment: String
        )
