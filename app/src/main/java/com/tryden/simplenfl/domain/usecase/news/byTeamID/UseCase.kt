package com.tryden.simplenfl.domain.usecase.news.byTeamID

import com.tryden.simplenfl.domain.models.news.Headline

/**
 * Interface for the NewsByTeamIdUseCase
 */
interface UseCase {

    suspend fun getNewsByTeamId(teamId: String, limit: String) : List<Headline>?

}