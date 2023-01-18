package com.tryden.simplenfl.network.response.teams.models.scoreboard

data class Scoreboard(
    val events: List<Event> = listOf(),
    val leagues: List<League> = listOf()
)