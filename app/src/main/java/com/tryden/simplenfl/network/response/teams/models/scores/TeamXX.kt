package com.tryden.simplenfl.network.response.teams.models.scores

data class TeamXX(
    val abbreviation: String = "",
    val alternateColor: String? = "",
    val color: String? = "",
    val displayName: String = "",
    val id: String = "",
    val isActive: Boolean = false,
    val links: List<LinkX> = listOf(),
    val location: String = "",
    val logo: String? = "",
    val name: String? = "",
    val shortDisplayName: String = "",
    val uid: String = "",
    val venue: Venue? = Venue()
)