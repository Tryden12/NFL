package com.tryden.simplenfl.network.response.models.team

data class Competitor(
    val homeAway: String = "",
    val id: String = "",
    val order: Int = 0,
    val team: com.tryden.simplenfl.network.response.models.team.Team = com.tryden.simplenfl.network.response.models.team.Team(),
    val type: String = ""
)