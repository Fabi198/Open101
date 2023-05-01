package com.example.open101.superhero

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSuperheroClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://superheroapi.com/api/5754777561316657/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()

    val APISuperheroService: APISuperheroService = retrofit.create(com.example.open101.superhero.APISuperheroService::class.java)
}