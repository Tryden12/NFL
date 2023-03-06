package com.tryden.simplenfl.domain.models.news

data class FavoriteHeadline(
    val articleId: String = "",
    val headline: String = "",
    val shortDescription: String = "",
    val articleImage: String = "",
    val teamLogo: String = "",
    val teamName: String = "",
    val teamColor: String = "",
    val timeSincePosted: String = "",
    val author: String = "",
)