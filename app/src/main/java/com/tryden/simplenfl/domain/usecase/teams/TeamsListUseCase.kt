package com.tryden.simplenfl.domain.usecase.teams

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.domain.newmapper.TeamsListMapper
import com.tryden.simplenfl.domain.newmodels.TeamList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Following the clean architecture, this is the use case class for teams list.
 * This use case is later injected to the view model wherever it is required.
 *
 * We map the Dto models to the domain models here in the domain layer.
 */
class TeamsListUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val teamsListMapper: TeamsListMapper
) : UseCase {
    override fun getAllTeams(): Flow<List<TeamList>> {
        return dataRepository.getAllTeams().map { list ->
            list.map { value ->
                teamsListMapper.buildFrom(value.team)
            }
        }
    }

}