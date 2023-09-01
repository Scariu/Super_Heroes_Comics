package com.example.superheroescomics.data

import com.example.superheroescomics.data.local.SuperHeroDetailEntity
import com.example.superheroescomics.data.local.SuperHeroEntity
import com.example.superheroescomics.data.remote.SuperHero
import com.example.superheroescomics.data.remote.SuperHeroDetail

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
