package com.tryden.simplenfl.network

import com.tryden.simplenfl.network.response.models.article.ArticleResponse
import com.tryden.simplenfl.network.response.models.news.NewsResponse
import com.tryden.simplenfl.network.response.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse
import com.tryden.simplenfl.network.response.models.team.TeamResponse
import com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse
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

    suspend fun getAllTeams() : SimpleResponse<AllTeamsResponse> {
        return safeApiCall { nflService.getAllTeams() }
    }

    suspend fun getTeamById(teamId: String) : SimpleResponse<TeamResponse> {
        return safeApiCall { nflService.getTeamById(teamId) }
    }

    suspend fun getRosterByTeamId(teamId: String) : SimpleResponse<RosterResponse> {
        return safeApiCall { nflService.getRosterByTeamId(teamId) }
    }

    suspend fun getScoresRange(dates: String, limit: String) : SimpleResponse<ScoreboardResponse> {
        return safeApiCall { nflService.getScoresRange(dates, limit) }
    }

    suspend fun getScoresByWeek(week: String) : SimpleResponse<ScoreboardResponse> {
        return safeApiCall { nflService.getScoresByWeek(week) }
    }

    suspend fun getScoresCalendar(limit: String) : SimpleResponse<ScoreboardResponse> {
        return safeApiCall { nflService.getScoresCalendar(limit) }
    }

    suspend fun getBreakingNews(type: String, limit: String): SimpleResponse<NewsResponse> {
        return safeApiCall { nflService.getBreakingNews(type, limit) }
    }

    suspend fun getNewsByTeamId(teamId: String, limit: String): SimpleResponse<NewsResponse> {
        return safeApiCall { nflService.getNewsByTeamId(teamId, limit) }
    }

    suspend fun getArticleById(articleId: String) : SimpleResponse<ArticleResponse> {
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