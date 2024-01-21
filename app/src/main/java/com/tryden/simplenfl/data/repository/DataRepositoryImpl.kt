package com.tryden.simplenfl.data.repository

import android.util.Log
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.data.local.source.LocalSource
import com.tryden.simplenfl.data.remote.Resource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.ArticleDto
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.source.RemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Data is fetched here, either from remote or local data source.
 * The Dto models are later mapped to domain layer models in the domain layer for their use case.
 */
class DataRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteSource,
    private val localDataSource: LocalSource
) : DataRepository {


    /**
     * Remote source
     */
    // region teams(s)
    override fun getAllTeams(): Flow<List<AllTeamsDto.Teams>> {
        return flow {
            remoteDataSource.getAllTeams().data?.let { list ->
                Log.d("DataRepositoryImpl", "Teams list size: ${list.size}")
                emit(list)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTeamById(teamId: String): TeamDto.Team? {
        return remoteDataSource.getTeamById(teamId).data
    }

    // endregion team(s)

    // region news
    override suspend fun getNews(type: String, limit: String): List<NewsDto.Article>? {
        return remoteDataSource.getNews(type, limit).data
    }

    override suspend fun getNewsByTeamId(teamId: String, limit: String): List<NewsDto.Article>? {
        return remoteDataSource.getNewsByTeamId(teamId, limit).data
    }

    override suspend fun getArticleById(id: String): ArticleDto.Headline? {
        return remoteDataSource.getArticleById(id).data
    }
    // endregion news

    /**
     * Local source
     */
    // region Local data source: Favorite Team Dao
    override fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>> {
        return localDataSource.getAllFavoriteTeams()
    }

    override suspend fun insertFavoriteTeam(entity: FavoriteTeamEntity) {
        localDataSource.insertFavoriteTeam(entity)
    }

    override suspend fun deleteFavoriteTeam(entity: FavoriteTeamEntity) {
        localDataSource.deleteFavoriteTeam(entity)
    }

    override suspend fun updateFavoriteTeam(entity: FavoriteTeamEntity) {
        localDataSource.updateFavoriteTeam(entity)
    }
    // endregion Local data source: Favorite Team Dao

}