package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse

class TeamsRepository {
    suspend fun getAllTeams(): com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse? {
        val request = NetworkLayer.apiClient.getAllTeams()

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}