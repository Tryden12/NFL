package com.tryden.simplenfl.domain.newmapper

import com.tryden.simplenfl.data.remote.dto.ArticleDto
import com.tryden.simplenfl.domain.addBrTags
import com.tryden.simplenfl.domain.models.article.Article

class ArticleMapper {

    fun buildFrom(articleDto: ArticleDto.Headline) : Article {
        return Article(
            headline = articleDto.headline,
            headerImage = articleDto.images[0].url,
            authorName = articleDto.byline,
            authorImage = "",
            source = articleDto.source,
            date = articleDto.published,
            story = addBrTags(articleDto.story)
        )
    }
}