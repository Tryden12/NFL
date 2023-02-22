package com.tryden.simplenfl.network.response.models.scores

data class Event(
    val competitions: List<com.tryden.simplenfl.network.response.models.scores.Competition> = listOf(),
    val date: String = "",
    val id: String = "",
    val links: List<com.tryden.simplenfl.network.response.models.scores.LinkXXXX> = listOf(),
    val name: String = "",
    val season: com.tryden.simplenfl.network.response.models.scores.Season = com.tryden.simplenfl.network.response.models.scores.Season(),
    val shortName: String = "",
    val status: com.tryden.simplenfl.network.response.models.scores.Status = com.tryden.simplenfl.network.response.models.scores.Status(),
    val uid: String = "",
    val weather: com.tryden.simplenfl.network.response.models.scores.Weather? = com.tryden.simplenfl.network.response.models.scores.Weather(),
    val week: com.tryden.simplenfl.network.response.models.scores.Week = com.tryden.simplenfl.network.response.models.scores.Week()
)