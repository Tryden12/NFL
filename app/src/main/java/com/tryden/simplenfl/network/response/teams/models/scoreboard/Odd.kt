package com.tryden.simplenfl.network.response.teams.models.scoreboard

data class Odd(
    val details: String = "",
    val overUnder: Int = 0,
    val provider: Provider = Provider()
)