package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.domain.mappers.article.ArticleMapper
import com.tryden.simplenfl.domain.models.article.Article
import com.tryden.simplenfl.data.remote.NetworkLayer

class ArticleRepository {

    // Article by id response
    suspend fun getArticleById(articleId: String): Article? {
        val request = NetworkLayer.apiClient.getArticleById(articleId)

        if (request.failed || !request.isSuccessful) { return null }

        return ArticleMapper.buildFrom(request.body.headlines[0])
    }
}