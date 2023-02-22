package com.tryden.simplenfl.network.response.models.scores

data class LeaderXXX(
    val athlete: com.tryden.simplenfl.network.response.models.scores.AthleteX = com.tryden.simplenfl.network.response.models.scores.AthleteX(),
    val displayValue: String = "",
    val team: com.tryden.simplenfl.network.response.models.scores.TeamX = com.tryden.simplenfl.network.response.models.scores.TeamX(),
    val value: Int = 0
)