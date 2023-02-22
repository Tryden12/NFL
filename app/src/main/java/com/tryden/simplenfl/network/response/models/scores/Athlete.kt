package com.tryden.simplenfl.network.response.models.scores

data class Athlete(
    val active: Boolean = false,
    val displayName: String = "",
    val fullName: String = "",
    val headshot: String = "",
    val id: String = "",
    val jersey: String = "",
    val links: List<com.tryden.simplenfl.network.response.models.scores.Link> = listOf(),
    val position: com.tryden.simplenfl.network.response.models.scores.Position = com.tryden.simplenfl.network.response.models.scores.Position(),
    val shortName: String = "",
    val team: com.tryden.simplenfl.network.response.models.scores.TeamX = com.tryden.simplenfl.network.response.models.scores.TeamX()
)