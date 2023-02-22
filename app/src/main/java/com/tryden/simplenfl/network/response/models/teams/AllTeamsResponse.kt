package com.tryden.simplenfl.network.response.models.teams

data class AllTeamsResponse(
    val sports: List<com.tryden.simplenfl.network.response.models.teams.Sport> = listOf()
)