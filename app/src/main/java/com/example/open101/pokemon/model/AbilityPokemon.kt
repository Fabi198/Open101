package com.example.open101.pokemon.model

import com.google.gson.annotations.SerializedName

data class AbilityPokemon (
    @SerializedName("name") val nameAbility: String,
    @SerializedName("url") val urlAbility: String
)
