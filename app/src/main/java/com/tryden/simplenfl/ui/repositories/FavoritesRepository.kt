package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.data.local.dao.FavoriteTeamDao
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.domain.mappers.news.HeadlinesMapper
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.ui.formatPublishedForSorting
import com.tryden.simplenfl.data.remote.NetworkLayer
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(private val favoriteTeamDao: FavoriteTeamDao) {

    fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>> {
        return favoriteTeamDao.getAllFavoriteTeams()
    }

    suspend fun insert(entity: FavoriteTeamEntity) {
        favoriteTeamDao.insertFavoriteTeam(entity)
    }

    suspend fun delete(entity: FavoriteTeamEntity) {
        favoriteTeamDao.deleteFavoriteTeam(entity)
    }

    suspend fun update(entity: FavoriteTeamEntity) {
        favoriteTeamDao.updateFavoriteTeam(entity)
    }

    // News by team id
    suspend fun getHeadlinesByTeamId(teamId: String, limit: String): List<Headline>? {
        val request = NetworkLayer.apiClient.getNewsByTeamId(teamId, limit)

        if (request.failed || !request.isSuccessful) { return null }

        val articles = request.body.articles

        return buildList {
            articles
                .filter { it.type == "HeadlineNews" }
                .sortedByDescending { formatPublishedForSorting(it.published) }
                .forEachIndexed { index, article ->
                    if (index < 4) add(HeadlinesMapper.buildFrom(article))
                }
        }
    }

}