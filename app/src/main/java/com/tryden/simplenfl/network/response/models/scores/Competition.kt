package com.tryden.simplenfl.network.response.models.scores

data class Competition(
    val attendance: Int = 0,
    val broadcasts: List<com.tryden.simplenfl.network.response.models.scores.Broadcast> = listOf(),
    val competitors: List<com.tryden.simplenfl.network.response.models.scores.Competitor> = listOf(),
    val conferenceCompetition: Boolean = false,
    val date: String = "",
    val format: com.tryden.simplenfl.network.response.models.scores.Format = com.tryden.simplenfl.network.response.models.scores.Format(),
    val geoBroadcasts: List<com.tryden.simplenfl.network.response.models.scores.GeoBroadcast> = listOf(),
    val headlines: List<com.tryden.simplenfl.network.response.models.scores.Headline>? = listOf(),
    val id: String = "",
    val leaders: List<com.tryden.simplenfl.network.response.models.scores.LeaderXX>? = listOf(),
    val neutralSite: Boolean = false,
    val notes: List<com.tryden.simplenfl.network.response.models.scores.Note> = listOf(),
    val odds: List<com.tryden.simplenfl.network.response.models.scores.Odd>? = listOf(),
    val recent: Boolean = false,
    val startDate: String = "",
    val status: com.tryden.simplenfl.network.response.models.scores.Status = com.tryden.simplenfl.network.response.models.scores.Status(),
    val tickets: List<com.tryden.simplenfl.network.response.models.scores.Ticket>? = listOf(),
    val timeValid: Boolean = false,
    val type: com.tryden.simplenfl.network.response.models.scores.TypeXX = com.tryden.simplenfl.network.response.models.scores.TypeXX(),
    val uid: String = "",
    val venue: com.tryden.simplenfl.network.response.models.scores.VenueX? = com.tryden.simplenfl.network.response.models.scores.VenueX()
)