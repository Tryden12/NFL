package com.tryden.simplenfl.network.response.teams.models.team

data class Item(
    val description: String? = "",
    val stats: List<Stat> = listOf(),
    val summary: String = "",
    val type: String = ""
)