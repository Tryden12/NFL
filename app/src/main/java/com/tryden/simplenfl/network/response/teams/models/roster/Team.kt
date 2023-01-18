package com.tryden.simplenfl.network.response.teams.models.roster

data class Team(
    val abbreviation: String = "",
    val clubhouse: String = "",
    val color: String = "",
    val displayName: String = "",
    val groups: Groups = Groups(),
    val id: String = "",
    val location: String = "",
    val logo: String = "",
    val name: String = "",
    val recordSummary: String = "",
    val seasonSummary: String = "",
    val standingSummary: String = ""
)