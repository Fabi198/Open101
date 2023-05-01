package com.example.open101.simpsons

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitEpisodes {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.sampleapis.com/simpsons/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()


    val APIEpisodes: APIEpisodesService = retrofit.create(APIEpisodesService::class.java)
}