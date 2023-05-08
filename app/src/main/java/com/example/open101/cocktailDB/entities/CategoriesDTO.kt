package com.example.open101.cocktailDB.entities

import com.google.gson.annotations.SerializedName

data class CategoriesDTO (
    @SerializedName("drinks") val drinks: List<CategoryDTO>
        )
