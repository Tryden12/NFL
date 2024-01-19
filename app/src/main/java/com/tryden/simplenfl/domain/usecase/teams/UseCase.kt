package com.tryden.simplenfl.domain.usecase.teams

import com.tryden.simplenfl.domain.newmodels.TeamList
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the TeamsListUseCase
 */
interface UseCase {
    fun getAllTeams(): Flow<List<TeamList>>
}