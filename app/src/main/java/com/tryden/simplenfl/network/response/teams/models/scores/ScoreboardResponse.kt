package com.tryden.simplenfl.network.response.teams.models.scores

data class ScoreboardResponse(
    val events: List<Event> = listOf(),
    val leagues: List<League> = listOf()
)