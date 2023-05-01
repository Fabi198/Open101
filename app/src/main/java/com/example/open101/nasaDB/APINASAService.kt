package com.example.open101.nasaDB

import com.example.open101.nasaDB.entities.APOD
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APINASAService {

    @GET
    suspend fun getAPODService(@Url url: String): Response<APOD>

    @GET
    suspend fun getAPODCOUNTService(@Url url: String): Response<List<APOD>>
}