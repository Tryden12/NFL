package com.tryden.simplenfl.network.response.models.scores

data class Competitor(
    val homeAway: String = "",
    val id: String = "",
    val leaders: List<com.tryden.simplenfl.network.response.models.scores.Leader>? = listOf(),
    val linescores: List<com.tryden.simplenfl.network.response.models.scores.Linescore>? = listOf(),
    val order: Int = 0,
    val records: List<com.tryden.simplenfl.network.response.models.scores.Record> = listOf(),
    val score: String = "",
    val statistics: List<Any> = listOf(),
    val team: com.tryden.simplenfl.network.response.models.scores.TeamXX = com.tryden.simplenfl.network.response.models.scores.TeamXX(),
    val type: String = "",
    val uid: String = "",
    val winner: Boolean? = false
)