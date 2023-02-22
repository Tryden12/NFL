package com.tryden.simplenfl.network.response.models.scores


data class ScoreboardResponse(
    val events: List<com.tryden.simplenfl.network.response.models.scores.Event> = listOf(),
    val leagues: List<com.tryden.simplenfl.network.response.models.scores.League> = listOf(),
    val season: com.tryden.simplenfl.network.response.models.scores.SeasonScoreBoardResponse = com.tryden.simplenfl.network.response.models.scores.SeasonScoreBoardResponse(),
    val week: com.tryden.simplenfl.network.response.models.scores.Week = com.tryden.simplenfl.network.response.models.scores.Week()
)