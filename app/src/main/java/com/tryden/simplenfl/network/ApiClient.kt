package com.tryden.simplenfl.network

import com.tryden.simplenfl.network.response.teams.models.article.Article
import com.tryden.simplenfl.network.response.teams.models.news.News
import com.tryden.simplenfl.network.response.teams.models.roster.Roster
import com.tryden.simplenfl.network.response.teams.models.scoreboard.Scoreboard
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import com.tryden.simplenfl.network.response.teams.models.teams.Sports
import retrofit2.Response
import java.lang.Exception

class ApiClient(
    private val nflService: NFLService
) {

    suspend fun getAllTeams() : SimpleResponse<Sports> {
        return safeApiCall { nflService.getAllTeams() }
    }

    suspend fun getTeamById(teamId: Int) : SimpleResponse<TeamObject> {
        return safeApiCall { nflService.getTeamById(teamId) }
    }

    suspend fun getRosterByTeamId(teamId: Int) : SimpleResponse<Roster> {
        return safeApiCall { nflService.getRosterByTeamId(teamId) }
    }

    suspend fun getScoreboardRange() : SimpleResponse<Scoreboard> {
        return safeApiCall { nflService.getScoreboardRange() }
    }

    suspend fun getBreakingNews(): SimpleResponse<News> {
        return safeApiCall { nflService.getBreakingNews() }
    }

    suspend fun getArticleById(articleId: String) : SimpleResponse<Article> {
        return safeApiCall { nflService.getArticleById(articleId) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }


}