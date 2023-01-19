package com.tryden.simplenfl

import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.teams.models.article.Article
import com.tryden.simplenfl.network.response.teams.models.news.News
import com.tryden.simplenfl.network.response.teams.models.roster.Roster
import com.tryden.simplenfl.network.response.teams.models.scoreboard.Scoreboard
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import com.tryden.simplenfl.network.response.teams.models.teams.Sports

class SharedRepository {

    // Team by Id
    suspend fun getTeamById(teamId: Int): TeamObject? {
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

    // Get all teams
    suspend fun getAllTeams(): Sports? {
        val request = NetworkLayer.apiClient.getAllTeams()

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get roster by team id
    suspend fun getRosterByTeamId(teamId: Int): Roster? {
        val request = NetworkLayer.apiClient.getRosterByTeamId(teamId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get scoreboard by date range
    suspend fun getScoreboardRange(): Scoreboard? {
        val request = NetworkLayer.apiClient.getScoreboardRange()

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get breaking news
    suspend fun getBreakingNews(): News? {
        val request = NetworkLayer.apiClient.getBreakingNews()

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get article by id
    suspend fun getArticleById(articleId: String): Article? {
        val request = NetworkLayer.apiClient.getArticleById(articleId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

}