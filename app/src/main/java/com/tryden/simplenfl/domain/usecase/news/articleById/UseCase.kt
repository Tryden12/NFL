package com.tryden.simplenfl.domain.usecase.news.articleById

import com.tryden.simplenfl.domain.models.article.Article

/**
 * Interface for the ArticleByIdUseCase
 */
interface UseCase {
    suspend fun getArticleById(id: String) : Article?
}