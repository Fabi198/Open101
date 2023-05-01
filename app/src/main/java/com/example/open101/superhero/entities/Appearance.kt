package com.example.open101.superhero.entities

import com.google.gson.annotations.SerializedName

data class Appearance (
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    @SerializedName("eye-color") val eyecolor: String,
    @SerializedName("hair-color") val haircolor: String
)
