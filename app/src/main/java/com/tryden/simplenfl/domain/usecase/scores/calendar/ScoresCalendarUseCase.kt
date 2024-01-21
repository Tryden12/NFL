package com.tryden.simplenfl.domain.usecase.scores.calendar

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.domain.models.calendar.UiCalendar
import com.tryden.simplenfl.domain.newmapper.CalendarMapper
import javax.inject.Inject

class ScoresCalendarUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val calendarMapper: CalendarMapper
) : UseCase {
    override suspend fun getScoresCalendar(limit: String): List<UiCalendar> {
        return dataRepository.getScoresCalendar(limit).map { calendar ->
            calendarMapper.buildFrom(calendar)
        }
    }
}