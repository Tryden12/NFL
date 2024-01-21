package com.tryden.simplenfl.domain.usecase.scores.byRange

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.domain.newmapper.EventMapper
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import javax.inject.Inject

class ScoresRangeUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val eventMapper: EventMapper
) : UseCase {
    override suspend fun getScoresRange(dates: String, limit: String): List<EventEntity> {
        return dataRepository.getScoresRange(dates, limit).events.map { eventDto ->
            eventMapper.buildFrom(eventDto)
        }
    }
}