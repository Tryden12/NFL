package com.tryden.simplenfl.network.response.teams.models.scores

data class LeaderX(
    val athlete: Athlete = Athlete(),
    val displayValue: String = "",
    val team: TeamX = TeamX(),
    val value: Int = 0
)