package com.tryden.simplenfl.network.response.teams.models.scoreboard

data class Athlete(
    val active: Boolean = false,
    val displayName: String = "",
    val fullName: String = "",
    val headshot: String = "",
    val id: String = "",
    val jersey: String = "",
//    val links: List<LinkX> = listOf(),
    val position: Position = Position(),
    val shortName: String = "",
    val team: TeamId = TeamId()
)