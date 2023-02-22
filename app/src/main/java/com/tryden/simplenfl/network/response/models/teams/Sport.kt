package com.tryden.simplenfl.network.response.models.teams

data class Sport(
    val id: String = "",
    val leagues: List<com.tryden.simplenfl.network.response.models.teams.League> = listOf(),
    val name: String = "",
    val slug: String = "",
    val uid: String = ""
)