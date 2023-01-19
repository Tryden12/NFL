package com.tryden.simplenfl.network.response.teams.models.article

data class Article(
    val headlines: List<Headline> = listOf(),
    val resultsCount: Int = 0,
    val resultsLimit: Int = 0,
    val resultsOffset: Int = 0,
    val status: String = "",
    val timestamp: String = ""
)