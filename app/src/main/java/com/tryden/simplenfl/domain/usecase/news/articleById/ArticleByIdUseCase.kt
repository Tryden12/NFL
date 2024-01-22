package com.tryden.simplenfl.domain.usecase.news.articleById

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.domain.models.article.Article
import com.tryden.simplenfl.domain.newmapper.ArticleMapper
import javax.inject.Inject

/**
 * Following the clean architecture, this is the use case for article by ID.
 * This use case is later injected to the view model wherever it is required.
 *
 * We map the Dto models to the domain models here in the domain layer.
 */
class ArticleByIdUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val mapper: ArticleMapper
) : UseCase {
    override suspend fun getArticleById(id: String): Article {
        return mapper.buildFrom(
            dataRepository.getArticleById(id)
        )
    }
}