package com.example.open101.superhero

import com.example.open101.superhero.entities.SuperheroDto
import com.example.open101.superhero.entities.SuperheroNameDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APISuperheroService {

    @GET
    suspend fun getID(@Url url: String): Response<SuperheroDto>

    @GET
    suspend fun getName(@Url url: String): Response<SuperheroNameDto>
}