package com.tryden.simplenfl.network.response.teams.models.news

data class Article(
    val byline: String = "",
    val categories: List<Category> = listOf(),
    val description: String = "",
    val headline: String = "",
    val images: List<Image> = listOf(),
    val lastModified: String = "",
    val links: Links = Links(),
    val premium: Boolean = false,
    val published: String = "",
    val type: String = ""
)