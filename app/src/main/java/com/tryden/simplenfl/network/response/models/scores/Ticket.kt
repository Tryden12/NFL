package com.tryden.simplenfl.network.response.models.scores

data class Ticket(
    val links: List<com.tryden.simplenfl.network.response.models.scores.LinkXXX> = listOf(),
    val numberAvailable: Int = 0,
    val summary: String = ""
)