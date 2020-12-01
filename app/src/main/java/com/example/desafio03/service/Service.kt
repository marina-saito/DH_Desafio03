package com.example.desafio03.service

//import com.example.desafio03.entities.JsonMarvel

import com.example.desafio03.entities.JsonMarvel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// Endpoint
interface Service {
    @GET("characters/1009610/comics")
    suspend fun getComics(
            @Query("orderBy") orderBy: String,
            @Query("offset") offset: Int,
            @Query("limit") limit: Int,
            @Query("ts") ts: Int,
            @Query("apikey") apikey: String,
            @Query("hash") hash: String
    ): JsonMarvel
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
