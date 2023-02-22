package com.tryden.simplenfl.network.response.models.roster

data class RosterResponse(
    val athletes: List<com.tryden.simplenfl.network.response.models.roster.Athlete> = listOf(),
    val coach: List<com.tryden.simplenfl.network.response.models.roster.Coach> = listOf(),
    val season: com.tryden.simplenfl.network.response.models.roster.Season = com.tryden.simplenfl.network.response.models.roster.Season(),
    val status: String = "",
    val team: com.tryden.simplenfl.network.response.models.roster.Team = com.tryden.simplenfl.network.response.models.roster.Team(),
    val timestamp: String = ""
)