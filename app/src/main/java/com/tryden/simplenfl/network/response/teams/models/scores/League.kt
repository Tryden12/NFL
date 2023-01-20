package com.tryden.simplenfl.network.response.teams.models.scores

data class League(
    val abbreviation: String = "",
    val calendar: List<Any> = listOf(),
    val id: String = "",
    val logos: List<Logo> = listOf(),
    val name: String = "",
    val season: SeasonX = SeasonX(),
    val slug: String = "",
    val uid: String = ""
)