package com.example.open101.pokemon.model

import com.google.gson.annotations.SerializedName

data class ResponsePokemonDto (

    @SerializedName("abilities") val abilities: List<AbilitiesPokemon>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("forms") val evolution: List<EvolutionPokemon>,
    @SerializedName("game_indices") val gameIndices: List<GameIndex>

    )
