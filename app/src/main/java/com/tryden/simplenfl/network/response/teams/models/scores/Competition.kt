package com.tryden.simplenfl.network.response.teams.models.scores

data class Competition(
    val attendance: Int = 0,
    val broadcasts: List<Broadcast> = listOf(),
    val competitors: List<Competitor> = listOf(),
    val conferenceCompetition: Boolean = false,
    val date: String = "",
    val format: Format = Format(),
    val geoBroadcasts: List<GeoBroadcast> = listOf(),
    val headlines: List<Headline>? = listOf(),
    val id: String = "",
    val leaders: List<LeaderXX>? = listOf(),
    val neutralSite: Boolean = false,
    val notes: List<Note> = listOf(),
    val odds: List<Odd>? = listOf(),
    val recent: Boolean = false,
    val startDate: String = "",
    val status: Status = Status(),
    val tickets: List<Ticket>? = listOf(),
    val timeValid: Boolean = false,
    val type: TypeXX = TypeXX(),
    val uid: String = "",
    val venue: VenueX? = VenueX()
)