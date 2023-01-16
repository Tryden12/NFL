package com.tryden.simplenfl.teams.models.team

data class Venue(
    val address: Address = Address(),
    val capacity: Int = 0,
    val fullName: String = "",
    val grass: Boolean = false,
    val id: String = "",
    val images: List<Image> = listOf(),
    val indoor: Boolean = false
)