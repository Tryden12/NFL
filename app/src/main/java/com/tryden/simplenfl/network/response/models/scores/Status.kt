package com.tryden.simplenfl.network.response.models.scores

data class Status(
    val clock: Int = 0,
    val displayClock: String = "",
    val period: Int = 0,
    val type: com.tryden.simplenfl.network.response.models.scores.TypeX = com.tryden.simplenfl.network.response.models.scores.TypeX()
)