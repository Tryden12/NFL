package com.tryden.simplenfl.network.response.teams.models.scores

data class Odd(
    val details: String = "",
    val overUnder: Double = 0.0,
    val provider: Provider = Provider()
)