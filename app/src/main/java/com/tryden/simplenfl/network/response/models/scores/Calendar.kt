package com.tryden.simplenfl.network.response.models.scores

data class Calendar(
    val endDate: String? = "",
    val entries: List<com.tryden.simplenfl.network.response.models.scores.Entry>? = listOf(),
    val label: String? = "",
    val startDate: String? = "",
    val value: String? = ""
)