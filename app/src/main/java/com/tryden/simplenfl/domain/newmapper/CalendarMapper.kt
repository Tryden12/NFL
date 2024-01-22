package com.tryden.simplenfl.domain.newmapper

import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.domain.models.calendar.UiCalendar
import javax.inject.Inject

class CalendarMapper @Inject constructor(): Mapper<UiCalendar, ScoreboardDto.Calendar>{

    override fun buildFrom(calendar: ScoreboardDto.Calendar) : UiCalendar {
        return UiCalendar(
            weeks = calendar.entries!!.map { week ->
                WeekMapper().buildFrom(week)
            },
            label = calendar.label
        )
    }
}