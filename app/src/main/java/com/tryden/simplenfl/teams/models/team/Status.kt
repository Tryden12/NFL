package com.tryden.simplenfl.teams.models.team

data class Status(
    val clock: Int = 0,
    val displayClock: String = "",
    val period: Int = 0,
    val type: StatusType = StatusType()
)