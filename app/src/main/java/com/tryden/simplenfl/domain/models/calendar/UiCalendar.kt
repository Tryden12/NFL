package com.tryden.simplenfl.domain.models.calendar


data class UiCalendar(
    val weeks: List<UiWeek> = listOf(),
    val label: String? = "",
) {

    data class UiWeek(
        val longLabel: String = "",
        val shortLabel: String = "",
        val formattedDates: String = "",
        val range: String = ""
    )
}