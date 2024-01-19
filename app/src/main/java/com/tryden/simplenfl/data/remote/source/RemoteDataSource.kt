package com.tryden.simplenfl.data.remote.source

import android.util.Log
import com.tryden.simplenfl.data.remote.Resource
import com.tryden.simplenfl.data.remote.ResponseResource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.data.remote.service.NFLService
import javax.inject.Inject

/**
 * We fetch data from remote source: espn api's.
 * We utilize the ResponseResource class for Success, Loading, and DataError cases.
 */
class RemoteDataSource @Inject constructor(
    private val nflService: NFLService
) : RemoteSource {
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
}