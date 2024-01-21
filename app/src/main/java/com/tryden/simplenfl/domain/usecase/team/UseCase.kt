package com.tryden.simplenfl.domain.usecase.team

import com.tryden.simplenfl.domain.models.team.Team

/**
 * Interface for the getting team by id.
 */
interface UseCase {
    suspend fun getTeamById(teamId: String): Team?

}