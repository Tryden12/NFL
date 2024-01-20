package com.tryden.simplenfl.data.repository

import android.util.Log
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.data.local.source.LocalSource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
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


    // region Remote data source
    /**
     * We use flow on Dispatchers.IO thread to fetch the teams list data.
     */
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

    override suspend fun getNewsByTeamId(teamId: String, limit: String): List<NewsDto.Article>? {
        return remoteDataSource.getNewsByTeamId(teamId, limit).data
    }
    // endregion Remote data source

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