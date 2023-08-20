package com.tryden.simplenfl.ui.repositories

import android.util.Log
import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse

class TeamsRepository {
    suspend fun getAllTeams(): AllTeamsResponse? {
        val request = NetworkLayer.apiClient.getAllTeams()

        if (request.failed || !request.isSuccessful) {
            Log.d("TeamsRepository()", "getAllTeams size: null" )
            return null
        }

        Log.d("TeamsRepository()", "getAllTeams size: " + "${request.body.sports[0].leagues[0].teams.size} ")
        return request.body
    }
}