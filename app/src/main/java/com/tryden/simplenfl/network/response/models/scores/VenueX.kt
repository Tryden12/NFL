package com.tryden.simplenfl.network.response.models.scores

data class VenueX(
    val address: com.tryden.simplenfl.network.response.models.scores.Address = com.tryden.simplenfl.network.response.models.scores.Address(),
    val capacity: Int = 0,
    val fullName: String = "",
    val id: String = "",
    val indoor: Boolean = false
)