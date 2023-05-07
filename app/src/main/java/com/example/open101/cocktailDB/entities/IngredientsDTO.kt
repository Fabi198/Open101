package com.example.open101.cocktailDB.entities

import com.google.gson.annotations.SerializedName

data class IngredientsDTO (
    @SerializedName("ingredients") val ingredient: List<IngredientDTO>
        )