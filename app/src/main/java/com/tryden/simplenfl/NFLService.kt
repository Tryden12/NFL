package com.tryden.simplenfl

import com.tryden.simplenfl.teams.models.team.TeamObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NFLService {

    @GET("teams/{team-id}")
    suspend fun getTeamById(
        @Path("team-id") teamId: Int
    ): Response<TeamObject>
}