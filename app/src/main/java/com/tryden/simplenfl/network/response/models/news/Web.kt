package com.tryden.simplenfl.network.response.models.news

data class Web(
    val href: String = "",
    val short: com.tryden.simplenfl.network.response.models.news.Short = com.tryden.simplenfl.network.response.models.news.Short()
)