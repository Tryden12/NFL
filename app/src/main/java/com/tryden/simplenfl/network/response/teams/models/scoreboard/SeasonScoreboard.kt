package com.tryden.simplenfl.network.response.teams.models.scoreboard

data class SeasonScoreboard(
    val endDate: String = "",
    val startDate: String = "",
    val type: SeasonType = SeasonType(),
    val year: Int = 0
)