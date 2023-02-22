package com.tryden.simplenfl.network.response.models.scores

data class Odd(
    val details: String = "",
    val overUnder: Double = 0.0,
    val provider: com.tryden.simplenfl.network.response.models.scores.Provider = com.tryden.simplenfl.network.response.models.scores.Provider()
)