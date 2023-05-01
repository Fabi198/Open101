package com.example.open101.pokemon.model

import com.google.gson.annotations.SerializedName

data class Version (
    @SerializedName("name") val nameVersion: String,
    @SerializedName("url") val urlVersion: String
)
