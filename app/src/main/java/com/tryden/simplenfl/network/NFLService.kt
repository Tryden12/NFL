package com.tryden.simplenfl.network

import com.tryden.simplenfl.network.response.teams.models.scoreboard.Scoreboard
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NFLService {

    @GET("teams/{team-id}")
    suspend fun getTeamById(
        @Path("team-id") teamId: Int
    ): Response<TeamObject>

    //scoreboard?limit=1000&dates=20220908-20230108
    @GET("scoreboard")
    suspend fun getScoreboardRange(
//        @Query("dates") range: String
    ) : Response<Scoreboard>
}