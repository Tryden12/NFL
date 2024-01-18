package com.tryden.simplenfl.data.remote

import com.tryden.simplenfl.data.remote.dto.ArticleDto
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.RosterDto
import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.service.ArticleByIDService
import com.tryden.simplenfl.data.remote.service.NFLService
import com.tryden.simplenfl.data.remote.service.PlayerByIdService
import retrofit2.Response
import java.lang.Exception

class ApiClient(
    private val nflService: NFLService,
    private val articleByIDService: ArticleByIDService,
    private val playerByIdService: PlayerByIdService
) {

    suspend fun getAllTeams() : ResponseResource<AllTeamsDto> {
        return safeApiCall { nflService.getAllTeams() }
    }

    suspend fun getTeamById(teamId: String) : ResponseResource<TeamDto> {
        return safeApiCall { nflService.getTeamById(teamId) }
    }

    suspend fun getRosterByTeamId(teamId: String) : ResponseResource<RosterDto> {
        return safeApiCall { nflService.getRosterByTeamId(teamId) }
    }

    suspend fun getScoresRange(dates: String, limit: String) : ResponseResource<ScoreboardDto> {
        return safeApiCall { nflService.getScoresRange(dates, limit) }
    }

    suspend fun getScoresByWeek(week: String) : ResponseResource<ScoreboardDto> {
        return safeApiCall { nflService.getScoresByWeek(week) }
    }

    suspend fun getScoresCalendar(limit: String) : ResponseResource<ScoreboardDto> {
        return safeApiCall { nflService.getScoresCalendar(limit) }
    }

    suspend fun getBreakingNews(type: String, limit: String): ResponseResource<NewsDto> {
        return safeApiCall { nflService.getBreakingNews(type, limit) }
    }

    suspend fun getNewsByTeamId(teamId: String, limit: String): ResponseResource<NewsDto> {
        return safeApiCall { nflService.getNewsByTeamId(teamId, limit) }
    }

    suspend fun getArticleById(articleId: String) : ResponseResource<ArticleDto> {
        return safeApiCall { articleByIDService.getArticleById(articleId) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): ResponseResource<T> {
        return try {
            ResponseResource.success(apiCall.invoke())
        } catch (e: Exception) {
            ResponseResource.failure(e)
        }
    }


}