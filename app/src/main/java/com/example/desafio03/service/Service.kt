package com.example.desafio03.service

import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// Endpoint
interface Service {
    @GET("comics")
    suspend fun getComics(
            @Query("characters") characters: Int,
            @Query("orderBy") orderBy: String,
            @Query("offset") offset: Int,
            @Query("limit") limit: Int,
            @Query("ts") ts: Int,
            @Query("apikey") apikey: String,
            @Query("hash") hash: String
    ): JsonObject
}

// url
val urlApiMarvel = "https://gateway.marvel.com/v1/public/"

// Retrofit
val retrofit = Retrofit.Builder()
        .baseUrl(urlApiMarvel)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

// Passar instancia do retrofit para o service
val service: Service = retrofit.create(Service::class.java)
