package com.tryden.simplenfl.data.repository

import android.util.Log
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.source.RemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
) : DataRepository {

    /**
     * We use flow on Dispatchers.IO thread to fetch the teams list data.
     * We then map from DTO model to a UI model using TeamsListMapper object in Domain layer.
     */
    override fun getAllTeams(): Flow<List<AllTeamsDto.Teams>> {
        return flow {
            remoteSource.getAllTeams().data?.let { list ->
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
        return remoteSource.getTeamById(teamId).data
    }
}