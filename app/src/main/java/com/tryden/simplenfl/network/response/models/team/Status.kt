package com.tryden.simplenfl.network.response.models.team

data class Status(
    val clock: Int = 0,
    val displayClock: String = "",
    val period: Int = 0,
    val type: com.tryden.simplenfl.network.response.models.team.StatusType = com.tryden.simplenfl.network.response.models.team.StatusType()
)