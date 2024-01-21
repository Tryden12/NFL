package com.tryden.simplenfl.data.remote.source

import android.util.Log
import com.tryden.simplenfl.data.remote.Resource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.service.ArticleService
import com.tryden.simplenfl.data.remote.service.NFLService
import javax.inject.Inject

/**
 * We fetch data from remote source: espn api's.
 * We utilize the ResponseResource class for Success, Loading, and DataError cases.
 */
class RemoteDataSource @Inject constructor(
    private val nflService: NFLService,
    private val articleService: ArticleService
) : RemoteSource {

    /**
     * Teams
     */
    override suspend fun getAllTeams(): Resource<List<AllTeamsDto.Teams>> {
        try {
            val res = nflService.getAllTeams()

            when (res.isSuccessful) {
                true -> {
                    res.body()?.sports?.get(0)?.leagues?.get(0)?.teams.let { teamsList ->
                        Log.d("RemoteDataSource", "getAllTeams(): teamsList size -> ${teamsList?.size}" )

                        return Resource.Success(data = teamsList)
                    }
                }
                false -> {
                    Log.d("RemoteDataSource", "getAllTeams(): NOT SUCCESSFUL, res code: ${res.code()}" )
                    return Resource.DataError(errorCode = res.code())
                }

            }
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "getAllTeams(): Exception hash code: ${e.hashCode()}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }

    override suspend fun getTeamById(teamId: String): Resource<TeamDto.Team> {
        try {
            val res = nflService.getTeamById(teamId)

            when (res.isSuccessful) {
                true -> {
                    res.body()?.team.let { team ->
                        Log.d("RemoteDataSource", "getTeamById(): ${team?.name}, id: ${team?.id}" )

                        return Resource.Success(data = team)
                    }
                }
                false -> {
                    Log.d("RemoteDataSource", "getTeamById(): NOT SUCCESSFUL, res code: ${res.code()}" )
                    return Resource.DataError(errorCode = res.code())
                }

            }
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "getTeamById(): Exception hash code: ${e.hashCode()}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }

    /**
     * News
     */
    override suspend fun getNews(type: String, limit: String): Resource<List<NewsDto.Article>> {
        try {
            val res = nflService.getNews(type, limit)

            when (res.isSuccessful) {
                true -> {
                    res.body()?.articles.let { news ->
                        Log.d("RemoteDataSource", "getNews() size: ${news?.size}")

                        return Resource.Success(data = news)
                    }
                }
                false -> {
                    Log.d("RemoteDataSource", "getNews(): NOT SUCCESSFUL, res code: ${res.code()}" )
                    return Resource.DataError(errorCode = res.code())
                }
            }
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "getNews(): Exception hash code: ${e.hashCode()}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }

    // News by team ID
    override suspend fun getNewsByTeamId(teamId: String, limit: String): Resource<List<NewsDto.Article>> {
        try {
            val res = nflService.getNewsByTeamId(teamId, limit)

            when (res.isSuccessful) {
                true -> {
                    res.body()?.articles?.let { articles ->
                        Log.d("RemoteDataSource", "getNewsByTeamId(): ${articles.size}, teamId: $teamId" )
                        return Resource.Success(data = articles)
                    } ?: return Resource.DataError(errorCode = res.code())
                }
                false -> {
                    Log.d("RemoteDataSource", "getNewsByTeamId(): NOT SUCCESSFUL, res code: ${res.code()}" )
                    return Resource.DataError(errorCode = res.code())
                }
            }
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "getNewsByTeamId(): Exception hash code: ${e.hashCode()}")
            return Resource.DataError(errorCode = e.hashCode())
        }
    }
}