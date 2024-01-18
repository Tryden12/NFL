package com.tryden.simplenfl.data.remote.source

import com.tryden.simplenfl.data.remote.ResponseResource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto

interface RemoteSource {

    suspend fun getAllTeams() : ResponseResource<AllTeamsDto>

    suspend fun getTeamById(teamId: String) : ResponseResource<TeamDto>
}