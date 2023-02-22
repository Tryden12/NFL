package com.tryden.simplenfl.network.response.models.news

data class LinkX(
    val href: String = "",
    val isExternal: Boolean = false,
    val isPremium: Boolean = false,
    val language: String = "",
    val rel: List<String> = listOf(),
    val shortText: String = "",
    val text: String = ""
)