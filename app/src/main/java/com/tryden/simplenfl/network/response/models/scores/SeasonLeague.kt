package com.tryden.simplenfl.network.response.models.scores


data class SeasonLeague(
    val endDate: String = "",
    val startDate: String = "",
    val type: com.tryden.simplenfl.network.response.models.scores.TypeXXXX = com.tryden.simplenfl.network.response.models.scores.TypeXXXX(),
    val year: Int = 0
)