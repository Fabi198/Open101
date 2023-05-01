package com.example.open101.superhero.entities

import com.example.open101.superhero.entities.*
import com.google.gson.annotations.SerializedName

data class SuperheroDto (
    @SerializedName("response") var response: String,
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("powerstats") var powerstats: Powerstats,
    @SerializedName("biography") var biography: Biography,
    @SerializedName("appearance") var appearance: Appearance,
    @SerializedName("work") var work: Work,
    @SerializedName("connections") var connections: Connections,
    @SerializedName("image") var image: Image
        )