package com.tryden.simplenfl.network.response.models.scores

data class LeaderXX(
    val abbreviation: String = "",
    val displayName: String = "",
    val leaders: List<com.tryden.simplenfl.network.response.models.scores.LeaderXXX> = listOf(),
    val name: String = "",
    val shortDisplayName: String = ""
)