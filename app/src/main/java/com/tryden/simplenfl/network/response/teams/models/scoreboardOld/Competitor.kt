package com.tryden.simplenfl.network.response.teams.models.scoreboardOld

data class Competitor(
    val homeAway: String = "",
    val id: String = "",
    val linescores: List<Linescore> = listOf(),
    val order: Int = 0,
    val records: List<RecordCompetitor> = listOf(),
    val score: String = "",
    val statistics: List<Any> = listOf(),
    val team: TeamEvent = TeamEvent(),
    val type: String = "",
    val uid: String = "",
    val winner: Boolean = false
)