package com.example.open101.pokemon.model

import com.google.gson.annotations.SerializedName

data class EvolutionPokemon (
        @SerializedName("name") val nameEvolution: String,
        @SerializedName("url") val urlEvolution: String
)
