package com.example.open101.cocktailDB.entities

import com.google.gson.annotations.SerializedName

data class GlassesDTO (
    @SerializedName("drinks") val drinksDTO: List<GlassDTO>
        )
