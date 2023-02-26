package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.domain.models.roster.Player
import com.tryden.simplenfl.domain.mappers.team.TeamRosterMapper
import com.tryden.simplenfl.domain.models.roster.Roster
import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.models.news.NewsResponse
import com.tryden.simplenfl.network.response.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.models.team.TeamResponse
import com.tryden.simplenfl.ui.epoxy.interfaces.team.RosterEpoxyItem

class TeamRepository {

    // Team by id
    suspend fun getTeamById(teamId: String): TeamResponse? {
        val request = NetworkLayer.apiClient.getTeamById(teamId)

        // If the api call fails, network fails, or user loses internet
        // We will return null and prevent app from crashing
        if (request.failed) {
            return null
        }

        // When the call makes it to the server, were we authorized
        // Was it a 200, 401, 404, etc code
        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }

    // News by team id
    suspend fun getNewsByTeamId(teamId: String, limit: String): NewsResponse? {
        val request = NetworkLayer.apiClient.getNewsByTeamId(teamId, limit)

        if (request.failed || !request.isSuccessful) { return null }

        return request.body
    }

    // Roster by team id
    suspend fun getRosterByTeamId(teamId: String) : Map<String, List<Player>>? {
        val request = NetworkLayer.apiClient.getRosterByTeamId(teamId)

        if (request.failed || !request.isSuccessful) { return null }


        return buildMap {
            request.body.athletes.forEach { group ->
               val playersBuilt = buildList {
                   group.items.sortedBy { players ->
                       players.firstName
                   }.forEach { player ->
                       add(TeamRosterMapper.buildFrom(player))
                   }
               }
                put(group.position, playersBuilt)
            }
        }

        // WORKS
//        val rosterEpoxyItems = buildList {
//            request.body.athletes.groupBy {
//                it.position
//            }.forEach { mapEntry ->
//                add(RosterEpoxyItem.HeaderItem(header = mapEntry.key))
//                mapEntry.value.forEach { group ->
//                    group.items.forEach { player ->
//                        val playerBuilt = TeamRosterMapper.buildFrom(player)
//                        add(RosterEpoxyItem.PlayerItem(player = playerBuilt))
//                    }
//                }
//            }
//            add(RosterEpoxyItem.FooterItem)
//        }
//        return rosterEpoxyItems

    }
}