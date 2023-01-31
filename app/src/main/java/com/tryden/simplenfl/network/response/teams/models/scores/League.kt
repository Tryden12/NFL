package com.tryden.simplenfl.network.response.teams.models.scores

data class League(
    val abbreviation: String = "",
    val calendar: List<Calendar> = listOf(),
    val calendarEndDate: String = "",
    val calendarIsWhitelist: Boolean = false,
    val calendarStartDate: String = "",
    val calendarType: String = "",
    val id: String = "",
    val logos: List<Logo> = listOf(),
    val name: String = "",
    val season: SeasonLeague = SeasonLeague(),
    val slug: String = "",
    val uid: String = ""
)