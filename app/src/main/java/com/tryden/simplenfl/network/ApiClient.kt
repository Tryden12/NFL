package com.tryden.simplenfl.network

import com.tryden.simplenfl.network.response.teams.models.scoreboard.Scoreboard
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import retrofit2.Response
import java.lang.Exception

class ApiClient(
    private val nflService: NFLService
) {

    suspend fun getTeamById(teamId: Int) : SimpleResponse<TeamObject> {
        return safeApiCall { nflService.getTeamById(teamId) }
    }

    suspend fun getScoreboardRange() : SimpleResponse<Scoreboard> {
        return safeApiCall { nflService.getScoreboardRange() }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }


}