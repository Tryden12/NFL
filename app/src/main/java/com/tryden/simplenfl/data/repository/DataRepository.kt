package com.tryden.simplenfl.data.repository

import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import kotlinx.coroutines.flow.Flow

/**
 * In order to have a clean architecture, I have created this interface
 * and implementing it in DataRepositoryImpl class.
 */
interface DataRepository {

    /**
     * Remote data
     */
    fun getAllTeams(): Flow<List<AllTeamsDto.Teams>>

    suspend fun getTeamById(teamId: String): TeamDto.Team?


    /**
     * Local data
     */
    fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>>

    suspend fun insertFavoriteTeam(entity: FavoriteTeamEntity)

    suspend fun deleteFavoriteTeam(entity: FavoriteTeamEntity)

    suspend fun updateFavoriteTeam(entity: FavoriteTeamEntity)

}