package com.tryden.simplenfl.network.response.teams.models.scoreboardOld

import com.tryden.simplenfl.network.response.teams.models.team.Status

data class Event(
    val competitions: List<Competition> = listOf(),
    val date: String = "",
    val id: String = "",
//    val links: List<LinkXX> = listOf(),
    val name: String = "",
    val season: SeasonByEvent = SeasonByEvent(),
    val shortName: String = "",
    val status: Status = Status(),
    val uid: String = "",
    val week: Week = Week()
)