package com.tryden.simplenfl.network.response.teams.models.news

data class Category(
    val athlete: Athlete? = Athlete(),
    val athleteId: Int? = 0,
    val createDate: String = "",
    val description: String? = "",
    val guid: String? = "",
    val id: Int? = 0,
    val league: League? = League(),
    val leagueId: Int? = 0,
    val sportId: Int? = 0,
    val team: Team? = Team(),
    val teamId: Int? = 0,
    val topicId: Int? = 0,
    val type: String = "",
    val uid: String? = ""
)