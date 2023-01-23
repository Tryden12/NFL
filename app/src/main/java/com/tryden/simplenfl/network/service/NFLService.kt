package com.tryden.simplenfl.network.service

import com.tryden.simplenfl.network.response.teams.models.news.News
import com.tryden.simplenfl.network.response.teams.models.roster.Roster
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeams
import com.tryden.simplenfl.network.response.teams.models.scores.Scoreboard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NFLService {

    @GET("teams")
    suspend fun getAllTeams(): Response<AllTeams>

    @GET("teams/{team-id}")
    suspend fun getTeamById(
        @Path("team-id") teamId: Int
    ): Response<TeamObject>

    @GET("teams/{team-id}/roster")
    suspend fun getRosterByTeamId(
        @Path("team-id") teamId: Int
    ): Response<Roster>

    //scoreboard?limit=1000&dates=20220908-20230108
    @GET("scoreboard")
    suspend fun getScoreboardRange(
        @Query("dates") dates: String,
        @Query("limit") limit: String
    ): Response<Scoreboard>

    @GET("news")
    suspend fun getBreakingNews() : Response<News>

}