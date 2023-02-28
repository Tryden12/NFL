package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.domain.mappers.news.HeadlinesMapper
import com.tryden.simplenfl.domain.mappers.team.TeamLogoMapper
import com.tryden.simplenfl.domain.mappers.team.TeamMapper
import com.tryden.simplenfl.domain.models.roster.Player
import com.tryden.simplenfl.domain.mappers.team.TeamRosterMapper
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.domain.models.team.Logo
import com.tryden.simplenfl.domain.models.team.Team
import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.models.news.NewsResponse
import com.tryden.simplenfl.network.response.models.team.TeamResponse

class TeamRepository {

    // Team header
    suspend fun getTeamHeader(teamId: String): Team? {
        val request = NetworkLayer.apiClient.getTeamById(teamId)

        if (request.failed || !request.isSuccessful) return null

        return TeamMapper.buildFrom(request.body.team)
    }

    // Get Team Logo only
    suspend fun getTeamLogo(teamId: String) : Logo? {
        val request = NetworkLayer.apiClient.getTeamById(teamId)

        if (request.failed || !request.isSuccessful) return null

        return TeamLogoMapper.buildFrom(request.body.team.logos[0].href)
    }

    // News by team id
    suspend fun getHeadlinesByTeamId(teamId: String, limit: String): List<Headline>? {
        val request = NetworkLayer.apiClient.getNewsByTeamId(teamId, limit)

        if (request.failed || !request.isSuccessful) { return null }

        val articles = request.body.articles

        return buildList {
            articles
                .filter { it.type == "HeadlineNews" }
                .forEachIndexed { index, article ->
                    if (index < 8) add(HeadlinesMapper.buildFrom(article))
                }
        }
    }

    // Roster by team id
    suspend fun getRosterByTeamId(teamId: String) : Map<String, List<Player>>? {
        val request = NetworkLayer.apiClient.getRosterByTeamId(teamId)

        if (request.failed || !request.isSuccessful) { return null }

        // Return a map containing domain layer model (Player)
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
    }
}