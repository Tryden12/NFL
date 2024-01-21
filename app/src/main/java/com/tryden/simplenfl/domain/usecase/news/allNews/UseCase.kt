package com.tryden.simplenfl.domain.usecase.news.allNews

import com.tryden.simplenfl.domain.models.news.Headline

/**
 * Interface for the NewsByTeamIdUseCase
 */
interface UseCase {
    suspend fun getNews(type: String, limit: String): List<Headline>?

}