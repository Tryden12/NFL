package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.models.news.NewsResponse
import com.tryden.simplenfl.network.response.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.models.team.TeamResponse

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
    suspend fun getRosterByTeamId(teamId: String) : RosterResponse? {
        val request = NetworkLayer.apiClient.getRosterByTeamId(teamId)

        if (request.failed || !request.isSuccessful) { return null }

        return request.body
    }
}