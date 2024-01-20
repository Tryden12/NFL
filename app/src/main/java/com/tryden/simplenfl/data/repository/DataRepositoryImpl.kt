package com.tryden.simplenfl.data.repository

import android.util.Log
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.data.local.source.LocalSource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.source.RemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteSource,
    private val localDataSource: LocalSource
) : DataRepository {


    /**
     * We use flow on Dispatchers.IO thread to fetch the teams list data.
     * We then map from DTO model to a UI model using TeamsListMapper object in Domain layer.
     */
    override fun getAllTeams(): Flow<List<AllTeamsDto.Teams>> {
        return flow {
            remoteDataSource.getAllTeams().data?.let { list ->
                Log.d("DataRepositoryImpl", "Teams list size: ${list.size}")
                emit(list)
            }
        }.flowOn(Dispatchers.IO)
    }

    /**
     * We fetch the teams list data.
     * We then map from DTO model to a UI model using TeamMapper object in Domain layer.
     */
    override suspend fun getTeamById(teamId: String): TeamDto.Team? {
        return remoteDataSource.getTeamById(teamId).data
    }


    // region Favorite Team Dao
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
    // endregion Favorite Team Dao

}