package com.tryden.simplenfl.network.response.teams.models.article

data class Team(
    val description: String = "",
    val id: Int = 0,
    val links: LinksTeam = LinksTeam()
)