package com.tryden.simplenfl.network.response.teams.models.news


data class NewsResponse(
    val articles: List<Article> = listOf(),
    val header: String = "",
    val link: Link = Link()
)