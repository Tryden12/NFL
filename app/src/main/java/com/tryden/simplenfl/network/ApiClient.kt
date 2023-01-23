package com.tryden.simplenfl.network

import com.tryden.simplenfl.network.response.teams.models.article.Article
import com.tryden.simplenfl.network.response.teams.models.news.News
import com.tryden.simplenfl.network.response.teams.models.player.Player
import com.tryden.simplenfl.network.response.teams.models.roster.Roster
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeams
import com.tryden.simplenfl.network.response.teams.models.scores.Scoreboard
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

    suspend fun getAllTeams() : SimpleResponse<AllTeams> {
        return safeApiCall { nflService.getAllTeams() }
    }

    suspend fun getTeamById(teamId: Int) : SimpleResponse<TeamObject> {
        return safeApiCall { nflService.getTeamById(teamId) }
    }

    suspend fun getRosterByTeamId(teamId: Int) : SimpleResponse<Roster> {
        return safeApiCall { nflService.getRosterByTeamId(teamId) }
    }

    suspend fun getScoreboardRange(dates: String, limit: String) : SimpleResponse<Scoreboard> {
        return safeApiCall { nflService.getScoreboardRange(dates, limit) }
    }

    suspend fun getBreakingNews(): SimpleResponse<News> {
        return safeApiCall { nflService.getBreakingNews() }
    }

    suspend fun getArticleById(articleId: String) : SimpleResponse<Article> {
        return safeApiCall { articleByIDService.getArticleById(articleId) }
    }

    suspend fun getPlayerById(playerId: String): SimpleResponse<Player> {
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