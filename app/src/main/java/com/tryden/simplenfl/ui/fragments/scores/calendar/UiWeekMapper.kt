package com.tryden.simplenfl.ui.fragments.scores.calendar

import com.tryden.simplenfl.network.response.teams.models.scores.Entry
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class UiWeekMapper {

    fun buildFrom(week: Entry) : UiCalendar.UiWeek {
        return UiCalendar.UiWeek(
            longLabel = week.label,
            shortLabel = week.alternateLabel,
            formattedDates = week.detail,
            range = getWeekRange(week.startDate, week.endDate)
        )
    }

    private fun getWeekRange(startIso: String, endIso: String): String {
        val formatter = DateTimeFormatter.ofPattern("uMMdd")
        val startDateOffSet = OffsetDateTime.parse(startIso, DateTimeFormatter.ISO_DATE_TIME)
        val endDateOffSet = OffsetDateTime.parse(endIso, DateTimeFormatter.ISO_DATE_TIME)

        val start = startDateOffSet.format(formatter)
        val end = endDateOffSet.format(formatter)

        return "$start-$end"
    }

}