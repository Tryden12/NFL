package com.tryden.simplenfl.network.response.models.news

import com.tryden.simplenfl.network.response.models.article.ArticleResponse

data class Api(
    val news: News = News()
)