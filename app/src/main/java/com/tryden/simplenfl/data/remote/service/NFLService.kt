package com.tryden.simplenfl.data.remote.service

import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.RosterDto
import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NFLService {

    @GET("teams")
    suspend fun getAllTeams(): Response<AllTeamsDto>

    @GET("teams/{team-id}")
    suspend fun getTeamById(
        @Path("team-id") teamId: String
    ): Response<TeamDto>

    @GET("teams/{team-id}/roster")
    suspend fun getRosterByTeamId(
        @Path("team-id") teamId: String
    ): Response<RosterDto>

    //scoreboard?limit=1000&dates=20220908-20230108
    @GET("scoreboard")
    suspend fun getScoresRange(
        @Query("dates") dates: String,
        @Query("limit") limit: String
    ): Response<ScoreboardDto>

    @GET("scoreboard")
    suspend fun getScoresByWeek(
        @Query("week") week: String
    ): Response<ScoreboardDto>

    @GET("scoreboard")
    suspend fun getScoresCalendar(
        @Query("limit") limit: String
    ): Response<ScoreboardDto>

    @GET("news")
    suspend fun getBreakingNews(
        @Query("type") type: String,
        @Query("limit") limit: String
    ) : Response<NewsDto>

    @GET("news")
    suspend fun getNewsByTeamId(
        @Query("team") teamId: String,
        @Query("limit") limit: String
    ) : Response<NewsDto>


}