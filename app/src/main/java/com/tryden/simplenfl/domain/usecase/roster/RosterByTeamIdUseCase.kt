package com.tryden.simplenfl.domain.usecase.roster

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.domain.models.roster.Player
import com.tryden.simplenfl.domain.newmapper.RosterMapper
import javax.inject.Inject

/**
 * Following the clean architecture, this is the use case class for getting roster by team ID.
 * This use case is later injected to the view model wherever it is required.
 *
 * We map the Dto models to the domain models here in the domain layer.
 */
class RosterByTeamIdUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val rosterMapper: RosterMapper
) : UseCase {
    override suspend fun getRosterByTeamId(teamId: String): Map<String, List<Player>>? {
        // Build a map to group the players to correct side of the ball:
        // Offense, Defense, Special Teams and sort by first name
        return buildMap {
            dataRepository.getRosterByTeamId(teamId)?.forEach { group ->
                val playersBuilt = buildList {
                    group.items.sortedBy { players ->
                        players.firstName
                    }.forEach { player ->
                        add(rosterMapper.buildFrom(player))
                    }
                }
                put(group.position, playersBuilt)
            }
        }
    }
}