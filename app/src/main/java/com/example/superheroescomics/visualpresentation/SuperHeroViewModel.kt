package com.example.superheroescomics.visualpresentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroescomics.data.Repository
import com.example.superheroescomics.data.local.SuperHeroDAO
import com.example.superheroescomics.data.local.SuperHeroDatabase
import com.example.superheroescomics.data.remote.SuperHeroRetrofit
import kotlinx.coroutines.launch

class SuperHeroViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val api = SuperHeroRetrofit.getSuperHeroRetrofit()
        val cellPhoneDataBase: SuperHeroDAO =
            SuperHeroDatabase.getDataBase(application).getSuperHeroesDAO()
        repository = Repository(api, cellPhoneDataBase)
    }

    //Lista
    fun superHeroesLiveData() = repository.getSuperHeroesFromEntity()

    fun getSuperHeroesViewModel() = viewModelScope.launch { repository.getSuperHeroes() }
}