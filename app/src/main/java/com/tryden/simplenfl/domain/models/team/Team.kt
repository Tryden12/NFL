package com.tryden.simplenfl.domain.models.team

data class Team(
    val id: String = "",
    val shortName: String = "",
    val longName: String = "",
    val abbreviation: String = "",
    val color: String = "",
    val alternateColor: String = "",
    val logo: String = "",
    val record: String = "",
    val stadium: String = ""
) {
}