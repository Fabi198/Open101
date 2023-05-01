package com.example.open101.simpsons.entities

import com.google.gson.annotations.SerializedName

data class EpisodesDTO (
    @SerializedName("season") val season: Int,
    @SerializedName("episode") val episode: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("description") val description: String,
    @SerializedName("airDate") val airDate: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("id") val id: Int
        )
