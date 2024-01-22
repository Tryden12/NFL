package com.tryden.simplenfl.domain.models.calendar

import com.tryden.simplenfl.data.remote.dto.ScoreboardDto.Calendar

class UiCalendarMapper {

    fun buildFrom(calendar: Calendar) : UiCalendar {
        return UiCalendar(
            weeks = calendar.entries!!.map { week ->
                UiWeekMapper().buildFrom(week)
            },
            label = calendar.label
        )
    }

}