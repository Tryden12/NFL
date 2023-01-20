package com.tryden.simplenfl.network.response.teams.models.scoreboardOld

import com.tryden.simplenfl.network.response.teams.models.team.Status
import com.tryden.simplenfl.network.response.teams.models.team.Venue

data class Competition(
    val attendance: Int = 0,
    val broadcasts: List<BroadcastScoreboard> = listOf(),
    val competitors: List<Competitor> = listOf(),
    val conferenceCompetition: Boolean = false,
    val date: String = "",
    val format: Format = Format(),
    val geoBroadcasts: List<GeoBroadcast> = listOf(),
    val odds: List<Odd> = listOf(),
    val headlines: List<Headline> = listOf(),
    val id: String = "",
    val leaders: List<Leaders> = listOf(),
    val neutralSite: Boolean = false,
    val notes: List<Any> = listOf(),
    val recent: Boolean = false,
    val startDate: String = "",
    val status: Status = Status(),
    val timeValid: Boolean = false,
    val uid: String = "",
    val venue: Venue = Venue()
)