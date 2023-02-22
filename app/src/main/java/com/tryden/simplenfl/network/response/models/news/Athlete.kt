package com.tryden.simplenfl.network.response.models.news

data class Athlete(
    val description: String = "",
    val id: Int = 0,
    val links: com.tryden.simplenfl.network.response.models.news.LinksAthlete = com.tryden.simplenfl.network.response.models.news.LinksAthlete()
)