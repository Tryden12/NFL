package com.tryden.simplenfl

import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.teams.models.article.ArticleResponse
import com.tryden.simplenfl.network.response.teams.models.news.NewsResponse
import com.tryden.simplenfl.network.response.teams.models.player.PlayerResponse
import com.tryden.simplenfl.network.response.teams.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.teams.models.team.TeamObjectResponse
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeamsResponse
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse

class SharedRepository {

    // Team by Id
    suspend fun getTeamById(teamId: Int): TeamObjectResponse? {
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
    suspend fun getAllTeams(): AllTeamsResponse? {
        val request = NetworkLayer.apiClient.getAllTeams()

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get roster by team id
    suspend fun getRosterByTeamId(teamId: Int): RosterResponse? {
        val request = NetworkLayer.apiClient.getRosterByTeamId(teamId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get scoreboard by date range
    suspend fun getScoreboardRange(dates: String, limit: String): ScoreboardResponse? {
        val request = NetworkLayer.apiClient.getScoreboardRange(dates, limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun getScoresByWeek(week: String) : ScoreboardResponse? {
        val request = NetworkLayer.apiClient.getScoresByWeek(week)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
     }

    suspend fun getScoresCalendar(limit: String) : ScoreboardResponse? {
        val request = NetworkLayer.apiClient.getScoresCalendar(limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get breaking news
    suspend fun getBreakingNews(type: String, limit: String): NewsResponse? {
        val request = NetworkLayer.apiClient.getBreakingNews(type, limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get news by team id
    suspend fun getNewsByTeamId(teamId: String, limit: String): NewsResponse? {
        val request = NetworkLayer.apiClient.getNewsByTeamId(teamId, limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get article by id
    suspend fun getArticleById(articleId: String): ArticleResponse? {
        val request = NetworkLayer.apiClient.getArticleById(articleId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get player by id
    suspend fun getPlayerById(playerId: String): PlayerResponse? {
        val request = NetworkLayer.apiClient.getPlayerById(playerId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

}