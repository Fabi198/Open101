package com.example.open101.cocktailDB

import com.example.open101.cocktailDB.entities.CategoriesDTO
import com.example.open101.cocktailDB.entities.DrinksDTO
import com.example.open101.cocktailDB.entities.GlassesDTO
import com.example.open101.cocktailDB.entities.IngredientsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APICocktailService {

    @GET
    suspend fun getPopularDrinks(@Url url: String): Response<DrinksDTO>

    @GET
    suspend fun getIngredient(@Url url: String): Response<IngredientsDTO>

    @GET
    suspend fun getCategoriesList(@Url url: String): Response<CategoriesDTO>

    @GET
    suspend fun getGlassesList(@Url url: String): Response<GlassesDTO>

}