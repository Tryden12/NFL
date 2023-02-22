package com.tryden.simplenfl.network.response.models.news

data class Api(
    val news: com.tryden.simplenfl.network.response.models.news.NewsX = com.tryden.simplenfl.network.response.models.news.NewsX(),
    val self: com.tryden.simplenfl.network.response.models.news.Self = com.tryden.simplenfl.network.response.models.news.Self()
)