package com.tryden.simplenfl.domain.models.teams


data class AllTeams(
    val sports: List<Sport> = listOf()
) {

    data class Sport(
        val id: String = "",
        val leagues: List<League> = listOf(),
        val name: String = "",
        val slug: String = "",
        val uid: String = ""
    )

    data class League(
        val abbreviation: String = "",
        val id: String = "",
        val name: String = "",
        val shortName: String = "",
        val slug: String = "",
        val teams: List<Teams> = listOf(),
        val uid: String = ""
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
        val isActive: Boolean = false,
        val isAllStar: Boolean = false,
        val links: List<Link> = listOf(),
        val location: String = "",
        val logos: List<Logo> = listOf(),
        val name: String = "",
        val nickname: String = "",
        val shortDisplayName: String = "",
        val slug: String = "",
        val uid: String = ""
    )

    data class Link(
        val href: String = "",
        val isExternal: Boolean = false,
        val isPremium: Boolean = false,
        val language: String = "",
        val rel: List<String> = listOf(),
        val shortText: String? = "",
        val text: String = ""
    )

    data class Logo(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val rel: List<String> = listOf(),
        val width: Int = 0
    )
}