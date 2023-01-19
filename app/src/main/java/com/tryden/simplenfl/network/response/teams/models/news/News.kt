package com.tryden.simplenfl.network.response.teams.models.news


data class News(
    val articles: List<Article> = listOf(),
    val header: String = "",
    val link: Link = Link()
)