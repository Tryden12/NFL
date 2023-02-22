package com.tryden.simplenfl.network.response.models.scores

data class AthleteX(
    val active: Boolean = false,
    val displayName: String = "",
    val fullName: String = "",
    val headshot: String = "",
    val id: String = "",
    val jersey: String = "",
//    val links: List<LinkXX> = listOf(),
//    val position: PositionX = PositionX(),
    val shortName: String = "",
    val team: com.tryden.simplenfl.network.response.models.scores.TeamX = com.tryden.simplenfl.network.response.models.scores.TeamX()
)