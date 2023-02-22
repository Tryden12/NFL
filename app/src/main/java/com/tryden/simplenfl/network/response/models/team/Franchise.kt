package com.tryden.simplenfl.network.response.models.team

data class Franchise(
    val abbreviation: String = "",
    val color: String = "",
    val displayName: String = "",
    val id: String = "",
    val isActive: Boolean = false,
    val location: String = "",
    val name: String = "",
    val nickname: String = "",
    val shortDisplayName: String = "",
    val slug: String = "",
    val uid: String = "",
    val venue: com.tryden.simplenfl.network.response.models.team.Venue = com.tryden.simplenfl.network.response.models.team.Venue()
)