package com.example.superheroescomics.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SuperHeroAPI {

    //Lista
    @GET("superheroes/")
    suspend fun getDataSuperHero(): Response<List<SuperHero>>
}