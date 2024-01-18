package com.tryden.simplenfl.domain.mappers.news

import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.domain.getArticleIdFromUrl
import com.tryden.simplenfl.domain.models.news.Headline

object HeadlinesMapper {

    fun buildFrom(articleDto: NewsDto.Article) : Headline {
        return Headline(
            title = articleDto.headline,
            articleId = getArticleIdFromUrl(articleDto.links.api.news.href),
            shortDescription = articleDto.description,
            articleImage = articleDto.images[0].url,
            published = articleDto.published,
            author = articleDto.byline
        )
    }
}