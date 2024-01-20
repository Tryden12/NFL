package com.tryden.simplenfl.domain.usecase.news.byTeamID

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.domain.newmapper.HeadlinesMapper
import com.tryden.simplenfl.util.Constants
import javax.inject.Inject


/**
 * Following the clean architecture, this is the use case class for news by team id list.
 * This use case is later injected to the view model wherever it is required.
 *
 * We map the Dto models to the domain models here in the domain layer.
 */
class NewsByTeamIdUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val headlinesMapper: HeadlinesMapper
) : UseCase {
    override suspend fun getNewsByTeamId(teamId: String, limit: String): List<Headline>? {
        return dataRepository.getNewsByTeamId(teamId, limit)?.filter {
            it.type == Constants.HEADLINE_NEWS
        }?.map {
            headlinesMapper.buildFrom(it)
        }
    }
}