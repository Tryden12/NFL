package com.tryden.simplenfl.network.response.teams.models.scoreboard

data class Leader(
    val athlete: Athlete = Athlete(),
    val displayValue: String = "",
    val team: TeamId = TeamId(),
    val value: Int = 0
)