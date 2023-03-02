package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.database.dao.FavoriteTeamDao
import com.tryden.simplenfl.database.entity.FavoriteTeamEntity
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(private val favoriteTeamDao: FavoriteTeamDao) {

    fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>> {
        return favoriteTeamDao.getAllFavoriteTeams()
    }

    suspend fun insert(favoriteTeamEntity: FavoriteTeamEntity) {
        favoriteTeamDao.insert(favoriteTeamEntity)
    }

    suspend fun delete(favoriteTeamEntity: FavoriteTeamEntity) {
        favoriteTeamDao.delete(favoriteTeamEntity)
    }

    suspend fun update(favoriteTeamEntity: FavoriteTeamEntity) {
        favoriteTeamDao.update(favoriteTeamEntity)
    }
}