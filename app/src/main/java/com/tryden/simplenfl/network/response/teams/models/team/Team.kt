package com.tryden.simplenfl.network.response.teams.models.team

data class Team(
    val abbreviation: String = "",
    val alternateColor: String = "",
    val color: String = "",
    val displayName: String = "",
    val franchise: com.tryden.simplenfl.network.response.teams.models.team.Franchise = com.tryden.simplenfl.network.response.teams.models.team.Franchise(),
    val id: String = "",
    val isActive: Boolean = false,
    val location: String = "",
    val logos: List<com.tryden.simplenfl.network.response.teams.models.team.Logo> = listOf(),
    val name: String = "",
    val nextEvent: List<com.tryden.simplenfl.network.response.teams.models.team.NextEvent> = listOf(),
    val nickname: String = "",
    val record: com.tryden.simplenfl.network.response.teams.models.team.RecordTeam = com.tryden.simplenfl.network.response.teams.models.team.RecordTeam(),
    val shortDisplayName: String = "",
    val slug: String = "",
    val standingSummary: String = "",
    val uid: String = ""
)