package com.tryden.simplenfl.network.response.teams.models.scoreboard

data class League(
    val abbreviation: String = "",
    val calendar: List<Any> = listOf(),
    val id: String = "",
    val logos: List<LogoNFL> = listOf(),
    val name: String = "",
    val season: SeasonScoreboard = SeasonScoreboard(),
    val slug: String = "",
    val uid: String = ""
)