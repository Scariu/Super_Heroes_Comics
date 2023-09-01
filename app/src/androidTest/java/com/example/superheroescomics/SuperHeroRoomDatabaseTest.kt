package com.example.superheroescomics

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.superheroescomics.data.local.SuperHeroDAO
import com.example.superheroescomics.data.local.SuperHeroDatabase
import com.example.superheroescomics.data.local.list.SuperHeroEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import com.google.common.truth.Truth.assertThat

class SuperHeroRoomDatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var superHeroDao: SuperHeroDAO
    private lateinit var superHeroDatabase: SuperHeroDatabase

    @Before
    fun setUp() {
        //Fake
        val context = ApplicationProvider.getApplicationContext<Context>()
        superHeroDatabase =
            Room.inMemoryDatabaseBuilder(context, SuperHeroDatabase::class.java).build()
        superHeroDao = superHeroDatabase.getSuperHeroesDAO()
    }

    @After
    fun tearDown() {
        superHeroDatabase.close()
    }

    @Test
    fun insertSuperHeroes_empty() = runBlocking {
        // Given
        val superHeroList = listOf<SuperHeroEntity>()

        // When
        superHeroDao.insertSuperHeroes(superHeroList)

        // Then A
        val it = superHeroDao.getSuperHeroes().getOrAwaitValue()

        assertThat(it).isNotNull() // equivalente a ---> assertNotEquals(it, null)
        assertThat(it).isEmpty() // equivalente a ---> assertEquals(it.size, 0)


        // Then B
        superHeroDao.getSuperHeroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertSuperHeroes_happyCase_1element() = runBlocking {
        // Given
        val superHeroList =
            listOf(SuperHeroEntity(1, "Genos", "Japón", "www.superHero1.com", "Fire", 2016))

        // When
        superHeroDao.insertSuperHeroes(superHeroList)

        // Then
        superHeroDao.getSuperHeroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertSuperHeroes_happyCase_3elements() = runBlocking {
        // Given
        val superHeroList =
            listOf(
                SuperHeroEntity(1, "Saitama", "Japón", "www.superHero1.com", "Strenght", 2015),
                SuperHeroEntity(2, "Thor", "Midgard", "www.superHero2.com", "Thunder", 1990),
                SuperHeroEntity(3, "Bluebeatle", "Mexico", "www.superHero3.com", "Defense", 2023)
            )

        // When
        superHeroDao.insertSuperHeroes(superHeroList)

        // Then
        superHeroDao.getSuperHeroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserve.invoke()

            // Don't wait indefinitely if the LiveData is not set.
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }

        } finally {
            this.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}


