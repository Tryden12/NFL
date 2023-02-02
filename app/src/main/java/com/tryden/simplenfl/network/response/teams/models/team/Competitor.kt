package com.tryden.simplenfl.network.response.teams.models.team

data class Competitor(
    val homeAway: String = "",
    val id: String = "",
    val order: Int = 0,
    val team: Team = Team(),
    val type: String = ""
)