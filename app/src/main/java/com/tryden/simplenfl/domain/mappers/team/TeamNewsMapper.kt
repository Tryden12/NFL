package com.tryden.simplenfl.domain.mappers.team

import android.util.Log
import com.tryden.simplenfl.domain.models.team.ArticleHeadline
import com.tryden.simplenfl.network.response.models.news.Article

object TeamNewsMapper {
    fun buildFrom(article: Article) : ArticleHeadline {
        Log.e("Mapper", "getArticleIdFromUrl: ${article.links.api.news.href}" )
        return ArticleHeadline(
            type = article.type,
            articleHeadline = article.headline,
            articleId = getArticleIdFromUrl(article.links.api.news.href)
        )
    }

    private fun getArticleIdFromUrl(url: String): String {
        return url.split("sports/news/").get(1)
    }
}