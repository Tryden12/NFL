package com.tryden.simplenfl.network.response.models.team

import com.squareup.moshi.Json

data class Competition(
    val attendance: Int = 0,
    @Json(name = "boxscoreAvailable")
    val boxScoreAvailable: Boolean = false,
    val broadcasts: List<com.tryden.simplenfl.network.response.models.team.Broadcast> = listOf(),
    val competitors: List<com.tryden.simplenfl.network.response.models.team.Competitor> = listOf(),
    val date: String = "",
    val id: String = "",
    val neutralSite: Boolean = false,
    val notes: List<com.tryden.simplenfl.network.response.models.team.Note> = listOf(),
    val status: com.tryden.simplenfl.network.response.models.team.Status = com.tryden.simplenfl.network.response.models.team.Status(),
    val ticketsAvailable: Boolean = false,
    val timeValid: Boolean = false,
    val venue: com.tryden.simplenfl.network.response.models.team.Venue = com.tryden.simplenfl.network.response.models.team.Venue()
)