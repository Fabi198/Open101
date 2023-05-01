package com.example.open101.pokemon.model

import com.google.gson.annotations.SerializedName

data class AbilitiesPokemon (
        @SerializedName("ability") val ability: AbilityPokemon,
        @SerializedName("is_hidden") val isHidden: Boolean,
        @SerializedName("slot") val slot: Int
)
