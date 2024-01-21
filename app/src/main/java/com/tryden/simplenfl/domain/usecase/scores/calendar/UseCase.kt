package com.tryden.simplenfl.domain.usecase.scores.calendar

import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.domain.models.calendar.UiCalendar

/**
 * Interface for ScoresCalendarUseCase.
 */
interface UseCase {
    suspend fun getScoresCalendar(limit: String) : List<UiCalendar>
}