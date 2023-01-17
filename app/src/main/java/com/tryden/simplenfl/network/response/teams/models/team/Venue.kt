package com.tryden.simplenfl.network.response.teams.models.team

data class Venue(
    val address: com.tryden.simplenfl.network.response.teams.models.team.Address = com.tryden.simplenfl.network.response.teams.models.team.Address(),
    val capacity: Int = 0,
    val fullName: String = "",
    val grass: Boolean = false,
    val id: String = "",
    val images: List<com.tryden.simplenfl.network.response.teams.models.team.Image> = listOf(),
    val indoor: Boolean = false
)