package com.tryden.simplenfl.network.response.teams.models.scores

data class Calendar(
    val endDate: String? = "",
    val entries: List<Entry>? = listOf(),
    val label: String? = "",
    val startDate: String? = "",
    val value: String? = ""
)