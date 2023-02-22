package com.tryden.simplenfl.network.response.models.article

data class Category(
    val description: String = "",
    val id: Int = 0,
    val league: com.tryden.simplenfl.network.response.models.article.League? = com.tryden.simplenfl.network.response.models.article.League(),
    val leagueId: Int? = 0,
    val sportId: Int = 0,
    val team: com.tryden.simplenfl.network.response.models.article.Team? = com.tryden.simplenfl.network.response.models.article.Team(),
    val teamId: Int? = 0,
    val topicId: Int? = 0,
    val type: String = "",
    val uid: String? = ""
)