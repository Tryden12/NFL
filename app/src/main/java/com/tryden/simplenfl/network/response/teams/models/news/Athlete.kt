package com.tryden.simplenfl.network.response.teams.models.news

data class Athlete(
    val description: String = "",
    val id: Int = 0,
    val links: LinksAthlete = LinksAthlete()
)