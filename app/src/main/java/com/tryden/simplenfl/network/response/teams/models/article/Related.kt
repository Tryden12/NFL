package com.tryden.simplenfl.network.response.teams.models.article

data class Related(
    val byline: String = "",
    val dataSourceIdentifier: String? = "",
    val description: String = "",
    val headline: String = "",
    val id: Int = 0,
    val images: List<ImageRelated> = listOf(),
    val lastModified: String = "",
    val linkText: String = "",
    val links: LinksRelated = LinksRelated(),
    val premium: Boolean = false,
    val published: String = "",
    val title: String = "",
    val type: String = ""
)