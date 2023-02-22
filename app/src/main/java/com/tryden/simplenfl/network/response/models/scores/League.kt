package com.tryden.simplenfl.network.response.models.scores

data class League(
    val abbreviation: String = "",
    val calendar: List<com.tryden.simplenfl.network.response.models.scores.Calendar> = listOf(),
    val calendarEndDate: String = "",
    val calendarIsWhitelist: Boolean = false,
    val calendarStartDate: String = "",
    val calendarType: String = "",
    val id: String = "",
    val logos: List<com.tryden.simplenfl.network.response.models.scores.Logo> = listOf(),
    val name: String = "",
    val season: com.tryden.simplenfl.network.response.models.scores.SeasonLeague = com.tryden.simplenfl.network.response.models.scores.SeasonLeague(),
    val slug: String = "",
    val uid: String = ""
)