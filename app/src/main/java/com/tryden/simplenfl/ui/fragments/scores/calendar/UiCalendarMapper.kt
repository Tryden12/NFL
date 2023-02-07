package com.tryden.simplenfl.ui.fragments.scores.calendar

import com.tryden.simplenfl.network.response.teams.models.scores.Calendar

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