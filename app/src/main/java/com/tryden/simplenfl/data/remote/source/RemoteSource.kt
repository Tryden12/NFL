package com.tryden.simplenfl.data.remote.source

import com.tryden.simplenfl.data.remote.Resource
import com.tryden.simplenfl.data.remote.ResponseResource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.ArticleDto
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.RosterDto
import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.data.remote.dto.TeamDto

interface RemoteSource {

    suspend fun getAllTeams() : Resource<List<AllTeamsDto.Teams>>

    suspend fun getTeamById(teamId: String) : Resource<TeamDto.Team>

    suspend fun getNews(type: String, limit: String): Resource<List<NewsDto.Article>>

    suspend fun getNewsByTeamId(teamId: String, limit: String) : Resource<List<NewsDto.Article>>

    suspend fun getRosterByTeamId(teamId: String) : Resource<List<RosterDto.Athlete>>

    suspend fun getArticleById(id: String) : Resource<ArticleDto.Headline>

    suspend fun getScoresRange(dates: String, limit: String) : Resource<ScoreboardDto>

    suspend fun getScoresCalendar(limit: String) : Resource<List<ScoreboardDto.Calendar>>
}