package com.example.superheroescomics.data

import com.example.superheroescomics.data.local.detail.SuperHeroDetailEntity
import com.example.superheroescomics.data.local.list.SuperHeroEntity
import com.example.superheroescomics.data.remote.list.SuperHero
import com.example.superheroescomics.data.remote.detail.SuperHeroDetail

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
