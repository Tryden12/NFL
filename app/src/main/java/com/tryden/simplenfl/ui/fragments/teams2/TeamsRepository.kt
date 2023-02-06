package com.tryden.simplenfl.ui.fragments.teams2

import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeamsResponse

class TeamsRepository {
    suspend fun getAllTeams(): AllTeamsResponse? {
        val request = NetworkLayer.apiClient.getAllTeams()

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}