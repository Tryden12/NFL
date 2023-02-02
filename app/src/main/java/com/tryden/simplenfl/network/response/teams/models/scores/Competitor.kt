package com.tryden.simplenfl.network.response.teams.models.scores

data class Competitor(
    val homeAway: String = "",
    val id: String = "",
    val leaders: List<Leader>? = listOf(),
    val linescores: List<Linescore>? = listOf(),
    val order: Int = 0,
    val records: List<Record>? = listOf(),
    val score: String = "",
    val statistics: List<Any> = listOf(),
    val team: TeamXX = TeamXX(),
    val type: String = "",
    val uid: String = "",
    val winner: Boolean? = false
)