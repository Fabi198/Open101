package com.example.open101.dogList

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIDogService {
    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogsResponse>
}