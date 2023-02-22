package com.tryden.simplenfl.network.response.models.article

data class Related(
    val byline: String = "",
    val dataSourceIdentifier: String? = "",
    val description: String = "",
    val headline: String = "",
    val id: Int = 0,
    val images: List<com.tryden.simplenfl.network.response.models.article.ImageRelated> = listOf(),
    val lastModified: String = "",
    val linkText: String = "",
    val links: com.tryden.simplenfl.network.response.models.article.LinksRelated = com.tryden.simplenfl.network.response.models.article.LinksRelated(),
    val premium: Boolean = false,
    val published: String = "",
    val title: String = "",
    val type: String = ""
)