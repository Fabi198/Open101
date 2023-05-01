package com.example.open101.simpsons

import com.example.open101.simpsons.entities.EpisodesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIEpisodesService {

    @GET
    suspend fun getEpisodes(@Url url: String): Response<List<EpisodesDTO>>
}