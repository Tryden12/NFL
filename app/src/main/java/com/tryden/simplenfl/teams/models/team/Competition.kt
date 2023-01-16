package com.tryden.simplenfl.teams.models.team

import com.squareup.moshi.Json

data class Competition(
    val attendance: Int = 0,
    @Json(name = "boxscoreAvailable")
    val boxScoreAvailable: Boolean = false,
    val broadcasts: List<Broadcast> = listOf(),
    val competitors: List<Competitor> = listOf(),
    val date: String = "",
    val id: String = "",
    val neutralSite: Boolean = false,
    val notes: List<Note> = listOf(),
    val status: Status = Status(),
    val ticketsAvailable: Boolean = false,
    val timeValid: Boolean = false,
    val venue: Venue = Venue()
)