package com.tryden.simplenfl.network.response.teams.models.team

data class Item(
    val description: String? = "",
    val stats: List<com.tryden.simplenfl.network.response.teams.models.team.Stat> = listOf(),
    val summary: String = "",
    val type: String = ""
)