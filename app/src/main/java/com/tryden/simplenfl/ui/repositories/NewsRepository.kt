package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.domain.mappers.news.HeadlinesMapper
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.network.NetworkLayer

class NewsRepository {

    // Get breaking news
    suspend fun getBreakingNews(type: String, limit: String): List<Headline>? {
        val request = NetworkLayer.apiClient.getBreakingNews(type, limit)

        if (request.failed || !request.isSuccessful) return null

        val articles = request.body.articles

        return buildList {
            articles
                .filter { it.type == "HeadlineNews" }
                .forEachIndexed { index, article ->
                    if (index < 8) { add(HeadlinesMapper.buildFrom(article))}
                }
        }
    }
}