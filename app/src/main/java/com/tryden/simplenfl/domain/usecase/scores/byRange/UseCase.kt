package com.tryden.simplenfl.domain.usecase.scores.byRange

import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity

/**
 * Interface for ScoresRangeUseCase.
 */
interface UseCase {

    suspend fun getScoresRange(dates: String, limit: String) : List<EventEntity>

}