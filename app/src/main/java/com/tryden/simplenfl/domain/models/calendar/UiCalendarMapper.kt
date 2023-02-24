package com.tryden.simplenfl.domain.models.calendar

import com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse.Calendar

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