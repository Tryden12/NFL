package com.tryden.simplenfl.network.response.models.news

data class Article(
    val byline: String = "",
    val categories: List<com.tryden.simplenfl.network.response.models.news.Category> = listOf(),
    val description: String = "",
    val headline: String = "",
    val images: List<com.tryden.simplenfl.network.response.models.news.Image> = listOf(),
    val lastModified: String = "",
    val links: com.tryden.simplenfl.network.response.models.news.Links = com.tryden.simplenfl.network.response.models.news.Links(),
    val premium: Boolean = false,
    val published: String = "",
    val type: String = ""
)