package com.tryden.simplenfl.domain.models.article

data class Article(
    val headline: String = "",
    val headerImage: String = "",
    val authorName: String = "",
    val authorImage: String = "",
    val source: String = "",
    val date: String = "",
    val story: String = ""
)