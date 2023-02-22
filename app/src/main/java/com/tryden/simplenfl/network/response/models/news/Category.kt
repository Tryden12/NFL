package com.tryden.simplenfl.network.response.models.news

data class Category(
    val athlete: com.tryden.simplenfl.network.response.models.news.Athlete? = com.tryden.simplenfl.network.response.models.news.Athlete(),
    val athleteId: Int? = 0,
    val createDate: String = "",
    val description: String? = "",
    val guid: String? = "",
    val id: Int? = 0,
    val league: com.tryden.simplenfl.network.response.models.news.League? = com.tryden.simplenfl.network.response.models.news.League(),
    val leagueId: Int? = 0,
    val sportId: Int? = 0,
    val team: com.tryden.simplenfl.network.response.models.news.Team? = com.tryden.simplenfl.network.response.models.news.Team(),
    val teamId: Int? = 0,
    val topicId: Int? = 0,
    val type: String = "",
    val uid: String? = ""
)