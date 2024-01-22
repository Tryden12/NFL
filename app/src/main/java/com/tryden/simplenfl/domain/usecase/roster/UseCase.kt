package com.tryden.simplenfl.domain.usecase.roster

import com.tryden.simplenfl.data.remote.dto.RosterDto
import com.tryden.simplenfl.domain.models.roster.Player

/**
 * Interface for the RosterByTeamIdUseCase.
 */
interface UseCase {
    suspend fun getRosterByTeamId(teamId: String) : Map<String, List<Player>>?

}