package com.tryden.simplenfl.network.response.models.roster

data class Team(
    val abbreviation: String = "",
    val clubhouse: String = "",
    val color: String = "",
    val displayName: String = "",
    val groups: com.tryden.simplenfl.network.response.models.roster.Groups = com.tryden.simplenfl.network.response.models.roster.Groups(),
    val id: String = "",
    val location: String = "",
    val logo: String = "",
    val name: String = "",
    val recordSummary: String = "",
    val seasonSummary: String = "",
    val standingSummary: String = ""
)