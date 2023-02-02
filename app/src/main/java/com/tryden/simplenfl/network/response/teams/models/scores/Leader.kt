package com.tryden.simplenfl.network.response.teams.models.scores

data class Leader(
    val abbreviation: String = "",
    val displayName: String = "",
    val leaders: List<LeaderX> = listOf(),
    val name: String = "",
    val shortDisplayName: String = ""
)