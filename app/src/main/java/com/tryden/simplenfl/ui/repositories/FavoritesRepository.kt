package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.database.dao.FavoriteTeamDao
import com.tryden.simplenfl.database.entity.FavoriteTeamEntity
import com.tryden.simplenfl.domain.mappers.news.HeadlinesMapper
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.network.NetworkLayer
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

    // News by team id
    suspend fun getHeadlinesByTeamId(teamId: String, limit: String): List<Headline>? {
        val request = NetworkLayer.apiClient.getNewsByTeamId(teamId, limit)

        if (request.failed || !request.isSuccessful) { return null }

        val articles = request.body.articles

        return buildList {
            articles
                .filter { it.type == "HeadlineNews" }
                .forEachIndexed { index, article ->
                    if (index < 4) add(HeadlinesMapper.buildFrom(article))
                }
        }
    }
}