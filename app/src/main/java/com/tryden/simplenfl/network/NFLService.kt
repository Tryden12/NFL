package com.tryden.simplenfl.network

import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NFLService {

    @GET("teams/{team-id}")
    suspend fun getTeamById(
        @Path("team-id") teamId: Int
    ): Response<com.tryden.simplenfl.network.response.teams.models.team.TeamObject>
}