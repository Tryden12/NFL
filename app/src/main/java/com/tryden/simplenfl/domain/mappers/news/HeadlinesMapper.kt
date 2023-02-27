package com.tryden.simplenfl.domain.mappers.news

import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.network.response.models.news.Article

object HeadlinesMapper {

    fun buildFrom(article: Article) : Headline {
        return Headline(title = article.headline)
    }
}