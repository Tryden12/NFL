package com.tryden.simplenfl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.tryden.simplenfl.data.local.AppDatabase
import com.tryden.simplenfl.data.local.dao.FavoriteTeamDao
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collect


@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoriteTeamDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var favoriteTeamDao: FavoriteTeamDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        favoriteTeamDao = database.favoriteTeamDao()
    }

    @After
    fun closeDatabase() = database.close()

    @Test
    fun insert_favoriteTeam_returnsTrue() = runBlocking {
        val team = FavoriteTeamEntity(
            id = "12345",
            shortName = "Tropics",
            longName = "Flint Tropics",
            abbreviation = "FLT",
            color = "#000000",
            logo = "www.somelogourl.com",
        )

        favoriteTeamDao.insertFavoriteTeam(team)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            favoriteTeamDao.getAllFavoriteTeams().collect {
                assertThat(it).contains(team)
                latch.countDown()
            }
            latch.await()
        }
        job.cancelAndJoin()
    }

    @Test
    fun update_favoriteTeam_returnsTrue() = runBlocking {
        // team
        val team = FavoriteTeamEntity(
            id = "12345",
            shortName = "Tropics",
            longName = "Flint Tropics",
            abbreviation = "FLT",
            color = "#000000",
            logo = "www.somelogourl.com",
        )

        // delete
        favoriteTeamDao.deleteFavoriteTeam(team)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            favoriteTeamDao.getAllFavoriteTeams().collect {
                assertThat(it).doesNotContain(team)
                latch.countDown()
            }
            latch.await()
        }
        job.cancelAndJoin()
    }

}