package com.tryden.simplenfl.network.response.models.roster

data class Athlete(
    val items: List<com.tryden.simplenfl.network.response.models.roster.Item> = listOf(),
    val position: String = ""
)