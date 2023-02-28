package com.tryden.simplenfl.network.response.models.news


data class NewsResponse(
    val articles: List<Article> = listOf(),
    val header: String = "",
)