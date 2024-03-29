package com.tryden.simplenfl.data.repository

import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.data.remote.Resource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.ArticleDto
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.RosterDto
import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import kotlinx.coroutines.flow.Flow

/**
 * In order to have a clean architecture, I have created this interface
 * and implementing it in DataRepositoryImpl class.
 */
interface DataRepository {

    /**
     * Remote data
     */
    fun getAllTeams(): Flow<List<AllTeamsDto.Teams>>

    suspend fun getTeamById(teamId: String): TeamDto.Team?

    suspend fun getRosterByTeamId(teamId: String) : List<RosterDto.Athlete>?

    suspend fun getNews(type: String, limit: String): List<NewsDto.Article>?

    suspend fun getNewsByTeamId(teamId: String, limit: String) : List<NewsDto.Article>?

    suspend fun getArticleById(id: String) : ArticleDto.Headline

    suspend fun getScoresRange(dates: String, limit: String) : ScoreboardDto

    suspend fun getScoresCalendar(limit: String) : List<ScoreboardDto.Calendar>

    /**
     * Local data
     */
    fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>>

    suspend fun insertFavoriteTeam(entity: FavoriteTeamEntity)

    suspend fun deleteFavoriteTeam(entity: FavoriteTeamEntity)

    suspend fun updateFavoriteTeam(entity: FavoriteTeamEntity)

}