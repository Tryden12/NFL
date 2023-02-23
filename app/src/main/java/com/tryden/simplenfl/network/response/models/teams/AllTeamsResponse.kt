package com.tryden.simplenfl.network.response.models.teams

data class AllTeamsResponse(
    val sports: List<Sport> = listOf()
) {
    data class Sport(
        val leagues: List<League> = listOf(),
    )

    data class League(
        val id: String = "",
        val name: String = "",
        val shortName: String = "",
        val teams: List<Teams> = listOf(),
    )

    data class Logo(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val rel: List<String> = listOf(),
        val width: Int = 0
    )

    data class Teams(
        val team: Team = Team()
    )

    data class Team(
        val abbreviation: String = "",
        val alternateColor: String = "",
        val color: String = "",
        val displayName: String = "",
        val id: String = "",
        val logos: List<Logo> = listOf(),
        val name: String = "",
        val nickname: String = "",
        val shortDisplayName: String = "",
    )
}