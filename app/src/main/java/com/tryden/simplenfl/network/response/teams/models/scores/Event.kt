package com.tryden.simplenfl.network.response.teams.models.scores

data class Event(
    val competitions: List<Competition> = listOf(),
    val date: String = "",
    val id: String = "",
    val links: List<LinkXXXX> = listOf(),
    val name: String = "",
    val season: Season = Season(),
    val shortName: String = "",
    val status: Status = Status(),
    val uid: String = "",
    val weather: Weather? = Weather(),
    val week: Week = Week()
)