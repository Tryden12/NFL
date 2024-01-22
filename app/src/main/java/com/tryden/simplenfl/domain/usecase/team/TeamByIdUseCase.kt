package com.tryden.simplenfl.domain.usecase.team

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.domain.models.team.Team
import com.tryden.simplenfl.domain.newmapper.TeamMapper
import javax.inject.Inject

/**
 * Following the clean architecture, this is the use case class for getting team by ID.
 * This use case is later injected to the view model wherever it is required.
 *
 * We map the Dto models to the domain models here in the domain layer.
 */
class TeamByIdUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val teamMapper: TeamMapper
) : UseCase {
    override suspend fun getTeamById(teamId: String): Team? {
        return dataRepository.getTeamById(teamId)?.let {
            teamMapper.buildFrom(it)
        }
    }
}