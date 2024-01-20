package com.tryden.simplenfl.data.local.source

import com.tryden.simplenfl.data.local.dao.FavoriteTeamDao
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favoriteTeamDao: FavoriteTeamDao
) : LocalSource {

    override fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>> {
        return favoriteTeamDao.getAllFavoriteTeams()
    }

    override suspend fun insertFavoriteTeam(entity: FavoriteTeamEntity) {
        return favoriteTeamDao.insertFavoriteTeam(entity)
    }

    override suspend fun deleteFavoriteTeam(entity: FavoriteTeamEntity) {
        return favoriteTeamDao.deleteFavoriteTeam(entity)
    }

    override suspend fun updateFavoriteTeam(entity: FavoriteTeamEntity) {
        return favoriteTeamDao.updateFavoriteTeam(entity)
    }
}