package com.tryden.simplenfl.data.remote.source

import com.tryden.simplenfl.data.remote.Resource
import com.tryden.simplenfl.data.remote.ResponseResource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.ArticleDto
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto

interface RemoteSource {

    suspend fun getAllTeams() : Resource<List<AllTeamsDto.Teams>>

    suspend fun getTeamById(teamId: String) : Resource<TeamDto.Team>

    suspend fun getNews(type: String, limit: String): Resource<List<NewsDto.Article>>

    suspend fun getNewsByTeamId(teamId: String, limit: String) : Resource<List<NewsDto.Article>>

    suspend fun getArticleById(id: String) : Resource<ArticleDto.Headline>
}