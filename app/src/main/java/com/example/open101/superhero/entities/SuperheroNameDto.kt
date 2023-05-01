package com.example.open101.superhero.entities

import com.google.gson.annotations.SerializedName

data class SuperheroNameDto(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperheroDto>
)
