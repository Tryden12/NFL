package com.tryden.simplenfl.data.remote.source

import com.tryden.simplenfl.data.remote.Resource
import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.remote.dto.TeamDto

interface RemoteSource {

    suspend fun getAllTeams() : Resource<List<AllTeamsDto.Teams>>

    suspend fun getTeamById(teamId: String) : Resource<TeamDto.Team>
}