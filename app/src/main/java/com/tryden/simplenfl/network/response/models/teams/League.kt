package com.tryden.simplenfl.network.response.models.teams

data class League(
    val abbreviation: String = "",
    val id: String = "",
    val name: String = "",
    val shortName: String = "",
    val slug: String = "",
    val teams: List<com.tryden.simplenfl.network.response.models.teams.Teams> = listOf(),
    val uid: String = ""
)