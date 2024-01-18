package com.tryden.simplenfl.data.remote

import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto

interface RemoteSource {

    suspend fun getAllTeams() : ResponseResource<AllTeamsDto>

    suspend fun getTeamById(teamId: String) : ResponseResource<TeamDto>
}