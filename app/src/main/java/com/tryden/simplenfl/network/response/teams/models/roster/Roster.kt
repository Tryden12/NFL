package com.tryden.simplenfl.network.response.teams.models.roster

data class Roster(
    val athletes: List<Athlete> = listOf(),
    val coach: List<Coach> = listOf(),
    val season: Season = Season(),
    val status: String = "",
    val team: Team = Team(),
    val timestamp: String = ""
)