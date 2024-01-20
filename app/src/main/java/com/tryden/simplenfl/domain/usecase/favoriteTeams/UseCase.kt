package com.tryden.simplenfl.domain.usecase.favoriteTeams

import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>>

    suspend fun insertFavoriteTeam(entity: FavoriteTeamEntity)

    suspend fun deleteFavoriteTeam(entity: FavoriteTeamEntity)

    suspend fun updateFavoriteTeam(entity: FavoriteTeamEntity)
}