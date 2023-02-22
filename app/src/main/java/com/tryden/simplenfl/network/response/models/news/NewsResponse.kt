package com.tryden.simplenfl.network.response.models.news


data class NewsResponse(
    val articles: List<com.tryden.simplenfl.network.response.models.news.Article> = listOf(),
    val header: String = "",
    val link: com.tryden.simplenfl.network.response.models.news.Link = com.tryden.simplenfl.network.response.models.news.Link()
)