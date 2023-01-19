package com.tryden.simplenfl.network.response.teams.models.teams

data class Sport(
    val id: String = "",
    val leagues: List<League> = listOf(),
    val name: String = "",
    val slug: String = "",
    val uid: String = ""
)