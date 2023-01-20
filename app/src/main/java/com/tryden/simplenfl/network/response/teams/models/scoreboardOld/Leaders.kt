package com.tryden.simplenfl.network.response.teams.models.scoreboardOld

data class Leaders(
    val abbreviation: String = "",
    val displayName: String = "",
    val leaders: List<Leader> = listOf(),
    val name: String = "",
    val shortDisplayName: String = ""
)