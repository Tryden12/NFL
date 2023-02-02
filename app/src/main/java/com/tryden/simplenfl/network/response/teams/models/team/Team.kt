package com.tryden.simplenfl.network.response.teams.models.team

data class Team(
    val abbreviation: String = "",
    val alternateColor: String = "",
    val color: String = "",
    val displayName: String = "",
    val franchise: Franchise = Franchise(),
    val id: String = "",
    val isActive: Boolean = false,
    val location: String = "",
    val logos: List<Logo> = listOf(),
    val name: String = "",
    val nextEvent: List<NextEvent> = listOf(),
    val nickname: String = "",
    val record: RecordTeam = RecordTeam(),
    val shortDisplayName: String = "",
    val slug: String = "",
    val standingSummary: String = "",
    val uid: String = ""
)