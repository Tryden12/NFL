package com.tryden.simplenfl.network.response.models.teams

data class Team(
    val abbreviation: String = "",
    val alternateColor: String = "",
    val color: String = "",
    val displayName: String = "",
    val id: String = "",
    val isActive: Boolean = false,
    val isAllStar: Boolean = false,
    val links: List<com.tryden.simplenfl.network.response.models.teams.Link> = listOf(),
    val location: String = "",
    val logos: List<com.tryden.simplenfl.network.response.models.teams.Logo> = listOf(),
    val name: String = "",
    val nickname: String = "",
    val shortDisplayName: String = "",
    val slug: String = "",
    val uid: String = ""
)