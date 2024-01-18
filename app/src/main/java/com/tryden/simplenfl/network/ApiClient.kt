package com.tryden.simplenfl.network

import com.tryden.simplenfl.data.remote.dto.ArticleDto
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.RosterDto
import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.network.service.ArticleByIDService
import com.tryden.simplenfl.network.service.NFLService
import com.tryden.simplenfl.network.service.PlayerByIdService
import retrofit2.Response
import java.lang.Exception

class ApiClient(
    private val nflService: NFLService,
    private val articleByIDService: ArticleByIDService,
    private val playerByIdService: PlayerByIdService
) {

    suspend fun getAllTeams() : SimpleResponse<AllTeamsDto> {
        return safeApiCall { nflService.getAllTeams() }
    }

    suspend fun getTeamById(teamId: String) : SimpleResponse<TeamDto> {
        return safeApiCall { nflService.getTeamById(teamId) }
    }

    suspend fun getRosterByTeamId(teamId: String) : SimpleResponse<RosterDto> {
        return safeApiCall { nflService.getRosterByTeamId(teamId) }
    }

    suspend fun getScoresRange(dates: String, limit: String) : SimpleResponse<ScoreboardDto> {
        return safeApiCall { nflService.getScoresRange(dates, limit) }
    }

    suspend fun getScoresByWeek(week: String) : SimpleResponse<ScoreboardDto> {
        return safeApiCall { nflService.getScoresByWeek(week) }
    }

    suspend fun getScoresCalendar(limit: String) : SimpleResponse<ScoreboardDto> {
        return safeApiCall { nflService.getScoresCalendar(limit) }
    }

    suspend fun getBreakingNews(type: String, limit: String): SimpleResponse<NewsDto> {
        return safeApiCall { nflService.getBreakingNews(type, limit) }
    }

    suspend fun getNewsByTeamId(teamId: String, limit: String): SimpleResponse<NewsDto> {
        return safeApiCall { nflService.getNewsByTeamId(teamId, limit) }
    }

    suspend fun getArticleById(articleId: String) : SimpleResponse<ArticleDto> {
        return safeApiCall { articleByIDService.getArticleById(articleId) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }


}