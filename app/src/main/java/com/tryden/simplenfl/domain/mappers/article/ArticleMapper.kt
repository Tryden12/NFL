package com.tryden.simplenfl.domain.mappers.article

import com.tryden.simplenfl.domain.addBrTags
import com.tryden.simplenfl.domain.models.article.Article
import com.tryden.simplenfl.network.response.models.article.ArticleResponse

object ArticleMapper {

    fun buildFrom(articleResponse: ArticleResponse.Headline) : Article {
        return Article(
            headline = articleResponse.headline,
            headerImage = articleResponse.images[0].url,
            authorName = articleResponse.byline,
            authorImage = "",
            source = articleResponse.source,
            date = articleResponse.published,
            story = addBrTags(articleResponse.story)
        )
    }
}