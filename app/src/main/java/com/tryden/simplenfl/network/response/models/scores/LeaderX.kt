package com.tryden.simplenfl.network.response.models.scores

data class LeaderX(
    val athlete: com.tryden.simplenfl.network.response.models.scores.Athlete = com.tryden.simplenfl.network.response.models.scores.Athlete(),
    val displayValue: String = "",
    val team: com.tryden.simplenfl.network.response.models.scores.TeamX = com.tryden.simplenfl.network.response.models.scores.TeamX(),
    val value: Int = 0
)