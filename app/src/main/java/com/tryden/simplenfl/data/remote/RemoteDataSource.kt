package com.tryden.simplenfl.data.remote

import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto

/**
 * We fetch data from remote source: espn api's.
 * We utilize the ResponseResource class for Success, Loading, and DataError cases.
 */
class RemoteDataSource : RemoteSource {
    override suspend fun getAllTeams(): ResponseResource<AllTeamsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeamById(teamId: String): ResponseResource<TeamDto> {
        TODO("Not yet implemented")
    }
}