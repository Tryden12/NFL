package com.tryden.simplenfl.network.response.models.article

data class ArticleResponse(
    val headlines: List<Headline> = listOf(),
) {
    data class Api(
        val news: News = News()
    )

    data class Category(
        val description: String = "",
        val id: Int = 0,
        val sportId: Int = 0,
        val team: Team? = Team(),
        val teamId: Int? = 0,
        val topicId: Int? = 0,
        val type: String = "",
    )

    data class Headline(
        val byline: String = "",
        val categories: List<Category> = listOf(),
        val description: String = "",
        val headline: String = "",
        val id: Int = 0,
        val images: List<Image> = listOf(),
        val keywords: List<Any> = listOf(),
        val lastModified: String = "",
        val linkText: String = "",
        val links: Links = Links(),
        val nowId: String = "",
        val originallyPosted: String = "",
        val premium: Boolean = false,
        val published: String = "",
        val related: List<Related> = listOf(),
        val source: String = "",
        val story: String = "",
        val title: String = "",
        val type: String = "",
        val video: List<Any> = listOf()
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

    data class ImageRelated(
        val alt: String? = "",
        val caption: String? = "",
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

    data class Mobile(
        val href: String = ""
    )

    data class News(
        val href: String = ""
    )

    data class Related(
        val byline: String = "",
        val description: String = "",
        val headline: String = "",
        val id: Int = 0,
        val images: List<ImageRelated> = listOf(),
        val lastModified: String = "",
        val linkText: String = "",
        val links: Links = Links(),
        val premium: Boolean = false,
        val published: String = "",
        val title: String = "",
    )

    data class Team(
        val description: String = "",
        val id: Int = 0,
    )


}