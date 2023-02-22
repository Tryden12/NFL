package com.tryden.simplenfl.network.response.models.article

data class Team(
    val description: String = "",
    val id: Int = 0,
    val links: com.tryden.simplenfl.network.response.models.article.LinksTeam = com.tryden.simplenfl.network.response.models.article.LinksTeam()
)