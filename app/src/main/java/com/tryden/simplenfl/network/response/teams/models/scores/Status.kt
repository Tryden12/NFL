package com.tryden.simplenfl.network.response.teams.models.scores

data class Status(
    val clock: Int = 0,
    val displayClock: String = "",
    val period: Int = 0,
    val type: TypeX = TypeX()
)