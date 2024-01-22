package com.tryden.simplenfl.domain.usecase.favoriteTeams

import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.data.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Following the clean architecture, this is the use case class for favorite teams.
 * This use case is later injected to the view model wherever it is required.
 *
 * We map the Dto models to the domain models here in the domain layer.
 */
class FavoriteTeamsUseCase @Inject constructor(
    private val dataRepository: DataRepository
) : UseCase {
    override fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>> {
        return dataRepository.getAllFavoriteTeams()
    }

    override suspend fun insertFavoriteTeam(entity: FavoriteTeamEntity) {
        dataRepository.insertFavoriteTeam(entity)
    }

    override suspend fun deleteFavoriteTeam(entity: FavoriteTeamEntity) {
        dataRepository.deleteFavoriteTeam(entity)
    }

    override suspend fun updateFavoriteTeam(entity: FavoriteTeamEntity) {
        dataRepository.updateFavoriteTeam(entity)
    }
}