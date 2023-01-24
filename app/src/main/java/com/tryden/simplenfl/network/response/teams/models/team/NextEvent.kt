package com.tryden.simplenfl.network.response.teams.models.team

data class NextEvent(
    val competitions: List<Competition> = listOf(),
    val date: String = "",
    val id: String = "",
    val name: String = "",
    val season: Season = Season(),
    val seasonType: SeasonType = SeasonType(),
    val shortName: String = "",
    val timeValid: Boolean = false,
    val week: Week = Week()
)