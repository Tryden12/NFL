package com.tryden.simplenfl.network

import com.tryden.simplenfl.network.response.teams.models.article.ArticleResponse
import com.tryden.simplenfl.network.response.teams.models.news.NewsResponse
import com.tryden.simplenfl.network.response.teams.models.player.PlayerResponse
import com.tryden.simplenfl.network.response.teams.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.teams.models.team.TeamObjectResponse
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeamsResponse
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
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

    suspend fun getTeamById(teamId: Int) : SimpleResponse<TeamObjectResponse> {
        return safeApiCall { nflService.getTeamById(teamId) }
    }

    suspend fun getRosterByTeamId(teamId: Int) : SimpleResponse<RosterResponse> {
        return safeApiCall { nflService.getRosterByTeamId(teamId) }
    }

    suspend fun getScoreboardRange(dates: String, limit: String) : SimpleResponse<ScoreboardResponse> {
        return safeApiCall { nflService.getScoreboardRange(dates, limit) }
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

    suspend fun getPlayerById(playerId: String): SimpleResponse<PlayerResponse> {
        return safeApiCall { playerByIdService.getPlayerById(playerId) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }


}