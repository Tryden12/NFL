package com.tryden.simplenfl.domain.mappers.team

import android.util.Log
import com.tryden.simplenfl.data.remote.dto.NewsDto
import com.tryden.simplenfl.domain.models.team.ArticleHeadline

object TeamNewsMapper {
    fun buildFrom(articleDto: NewsDto.Article) : ArticleHeadline {
        Log.e("Mapper", "getArticleIdFromUrl: ${articleDto.links.api.news.href}" )
        return ArticleHeadline(
            type = articleDto.type,
            articleHeadline = articleDto.headline,
            articleId = getArticleIdFromUrl(articleDto.links.api.news.href)
        )
    }

    private fun getArticleIdFromUrl(url: String): String {
        return url.split("sports/news/").get(1)
    }
}