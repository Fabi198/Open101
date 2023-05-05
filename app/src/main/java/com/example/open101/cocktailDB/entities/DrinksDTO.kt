package com.example.open101.cocktailDB.entities

import com.google.gson.annotations.SerializedName

data class DrinksDTO (@SerializedName("drinks") val Drinks: List<DrinkDTO>)