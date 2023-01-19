package com.tryden.simplenfl.network.response.teams.models.teams

data class League(
    val abbreviation: String = "",
    val id: String = "",
    val name: String = "",
    val shortName: String = "",
    val slug: String = "",
    val teams: List<Teams> = listOf(),
    val uid: String = ""
)