package com.tryden.simplenfl

import com.tryden.simplenfl.network.NetworkLayer

class SharedRepository {

    suspend fun getTeamById(teamId: Int): com.tryden.simplenfl.network.response.teams.models.team.TeamObject? {
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

}