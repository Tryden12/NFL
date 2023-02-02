package com.tryden.simplenfl.network.response.teams.models.roster

data class Athlete(
    val items: List<Item> = listOf(),
    val position: String = ""
)