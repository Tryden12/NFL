package com.tryden.simplenfl.network.response.models.scores

data class LinkX(
    val href: String = "",
    val isExternal: Boolean = false,
    val isPremium: Boolean = false,
    val rel: List<String> = listOf(),
    val text: String = ""
)