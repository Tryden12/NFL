package com.tryden.simplenfl.network.response.models.article

data class ArticleResponse(
    val headlines: List<com.tryden.simplenfl.network.response.models.article.Headline> = listOf(),
    val resultsCount: Int = 0,
    val resultsLimit: Int = 0,
    val resultsOffset: Int = 0,
    val status: String = "",
    val timestamp: String = ""
)