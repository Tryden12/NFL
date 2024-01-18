package com.tryden.simplenfl.data.remote.dto


data class NewsDto(
    val articles: List<Article> = listOf(),
    val header: String = "",
) {
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

    data class Category(
        val description: String = "",
        val id: Int = 0,
        val sportId: Int = 0,
        val teamId: Int? = 0,
        val topicId: Int? = 0,
        val type: String = "",
    )

    data class Image(
        val credit: String = "",
        val height: Int = 0,
        val id: Int = 0,
        val name: String = "",
        val type: String = "",
        val url: String = "",
        val width: Int = 0
    )

    data class Links(
        val api: Api = Api(),
        val mobile: Mobile = Mobile(),
    )

    data class Api(
        val news: News = News()
    )

    data class Mobile(
        val href: String = ""
    )

    data class News(
        val href: String = ""
    )
}