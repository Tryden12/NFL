package com.tryden.simplenfl

import com.tryden.simplenfl.teams.models.team.TeamObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NFLService {

    @GET("teams/{team-id}")
    fun getTeamById(
        @Path("team-id") teamId: Int
    ): Call<TeamObject>
}