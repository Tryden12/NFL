package com.tryden.simplenfl.network.response.teams.models.scores

data class VenueX(
    val address: Address = Address(),
    val capacity: Int = 0,
    val fullName: String = "",
    val id: String = "",
    val indoor: Boolean = false
)