package com.tryden.simplenfl

import com.tryden.simplenfl.network.NetworkLayer

class SharedRepository {

    // Team by Id
    suspend fun getTeamById(teamId: String): com.tryden.simplenfl.network.response.models.team.TeamResponse? {
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
    suspend fun getAllTeams(): com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse? {
        val request = NetworkLayer.apiClient.getAllTeams()

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get roster by team id
    suspend fun getRosterByTeamId(teamId: String): com.tryden.simplenfl.network.response.models.roster.RosterResponse? {
        val request = NetworkLayer.apiClient.getRosterByTeamId(teamId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get scoreboard by date range
    suspend fun getScoresRange(dates: String, limit: String): com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse? {
        val request = NetworkLayer.apiClient.getScoresRange(dates, limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun getScoresByWeek(week: String) : com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse? {
        val request = NetworkLayer.apiClient.getScoresByWeek(week)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
     }

    suspend fun getScoresCalendar(limit: String) : com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse? {
        val request = NetworkLayer.apiClient.getScoresCalendar(limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get breaking news
    suspend fun getBreakingNews(type: String, limit: String): com.tryden.simplenfl.network.response.models.news.NewsResponse? {
        val request = NetworkLayer.apiClient.getBreakingNews(type, limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get news by team id
    suspend fun getNewsByTeamId(teamId: String, limit: String): com.tryden.simplenfl.network.response.models.news.NewsResponse? {
        val request = NetworkLayer.apiClient.getNewsByTeamId(teamId, limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    // Get article by id
    suspend fun getArticleById(articleId: String): com.tryden.simplenfl.network.response.models.article.ArticleResponse? {
        val request = NetworkLayer.apiClient.getArticleById(articleId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

}