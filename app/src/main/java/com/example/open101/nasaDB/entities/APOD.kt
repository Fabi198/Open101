package com.example.open101.nasaDB.entities

import com.google.gson.annotations.SerializedName

data class APOD (
    @SerializedName("date") val date: String,
    @SerializedName("copyright") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("explanation") val desc: String,
    @SerializedName("url") val image: String
        )