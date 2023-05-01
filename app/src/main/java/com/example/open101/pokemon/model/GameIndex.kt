package com.example.open101.pokemon.model

import com.google.gson.annotations.SerializedName

data class GameIndex (
    @SerializedName("game_index") val gameIndex: Int,
    @SerializedName("version") val version: Version
)
