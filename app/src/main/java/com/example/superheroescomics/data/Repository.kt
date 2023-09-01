package com.example.superheroescomics.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.superheroescomics.data.local.SuperHeroDAO
import com.example.superheroescomics.data.local.SuperHeroDetailEntity
import com.example.superheroescomics.data.local.SuperHeroEntity
import com.example.superheroescomics.data.remote.SuperHero
import com.example.superheroescomics.data.remote.SuperHeroAPI
import com.example.superheroescomics.data.remote.SuperHeroDetail

class Repository(private val superHeroAPI: SuperHeroAPI, private val superHeroDAO: SuperHeroDAO) {

    //Lista
    fun getSuperHeroesFromEntity(): LiveData<List<SuperHeroEntity>> =
        superHeroDAO.getSuperHeroes()

    suspend fun getSuperHeroes() {
        try {
            val response = superHeroAPI.getDataSuperHero() // Aqui llegan los datos
            if (response.isSuccessful) { //Evalua si llegaron los datos
                val resp = response.body() // Solo obtiene la respuesta, no tiene status
                resp?.let {
                    val superHeroEntity = it.map { it.transformToEntity() }
                    superHeroDAO.insertSuperHeroes(superHeroEntity)
                }
            }
        } catch (exception: Exception) {
            Log.e("catch", "")
        }
    }

    //Detalle
    fun getSuperHeroDetailsFromEntity(id: Int): LiveData<SuperHeroDetailEntity> =
        superHeroDAO.getSuperHeroDetails(id)

    suspend fun getSuperHeroDetails(id: Int) {
        try {
            val response = superHeroAPI.getSuperHeroDetails(id) // Aqui llegan los datos
            if (response.isSuccessful) { //Evalua si llegaron los datos
                val resp = response.body() // Solo obtiene la respuesta, no tiene status
                resp?.let {
                    val superHeroDetailEntity = it.transformToDetailEntity()
                    superHeroDAO.insertSuperHeroDetails(superHeroDetailEntity)
                }
            }
        } catch (exception: Exception) {
            Log.e("catch", "")
        }
    }
}

fun SuperHero.transformToEntity(): SuperHeroEntity =
    SuperHeroEntity(this.id, this.name, this.origin, this.imageUrl, this.superPower, this.year)

fun SuperHeroDetail.transformToDetailEntity(): SuperHeroDetailEntity =
    SuperHeroDetailEntity(
        this.id,
        this.name,
        this.origin,
        this.imageUrl,
        this.superPower,
        this.year,
        this.color,
        this.translate
    )