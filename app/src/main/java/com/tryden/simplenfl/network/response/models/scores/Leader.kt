package com.tryden.simplenfl.network.response.models.scores

data class Leader(
    val abbreviation: String = "",
    val displayName: String = "",
    val leaders: List<com.tryden.simplenfl.network.response.models.scores.LeaderX> = listOf(),
    val name: String = "",
    val shortDisplayName: String = ""
)