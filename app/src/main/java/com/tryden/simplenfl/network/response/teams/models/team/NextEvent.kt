package com.tryden.simplenfl.network.response.teams.models.team

data class NextEvent(
    val competitions: List<com.tryden.simplenfl.network.response.teams.models.team.Competition> = listOf(),
    val date: String = "",
    val id: String = "",
    val name: String = "",
    val season: com.tryden.simplenfl.network.response.teams.models.team.Season = com.tryden.simplenfl.network.response.teams.models.team.Season(),
    val seasonType: com.tryden.simplenfl.network.response.teams.models.team.SeasonType = com.tryden.simplenfl.network.response.teams.models.team.SeasonType(),
    val shortName: String = "",
    val timeValid: Boolean = false,
    val week: com.tryden.simplenfl.network.response.teams.models.team.Week = com.tryden.simplenfl.network.response.teams.models.team.Week()
)