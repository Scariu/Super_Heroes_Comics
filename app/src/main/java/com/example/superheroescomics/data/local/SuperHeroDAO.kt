package com.example.superheroescomics.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperHeroDAO {
    //Lista
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHeroes(cellPhoneEntity: List<SuperHeroEntity>)

    @Query("Select * from super_hero_table order by id ASC")
    fun getSuperHeroes(): LiveData<List<SuperHeroEntity>>
}