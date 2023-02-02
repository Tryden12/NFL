package com.tryden.simplenfl.domain.models.roster


data class Roster(
    val athletes: List<Athlete> = listOf(),
    val coach: List<Coach> = listOf(),
    val season: Season = Season(),
    val team: Team = Team(),
) {

    data class AlternateIds(
        val sdr: String = ""
    )

    data class Athlete(
        val items: List<Item> = listOf(),
        val position: String = ""
    )

    data class BirthPlace(
        val city: String = "",
        val country: String = ""
    )

    data class Coach(
        val experience: Int = 0,
        val firstName: String = "",
        val id: String = "",
        val lastName: String = ""
    )

    data class Experience(
        val years: Int = 0
    )

    data class Groups(
        val `$ref`: String = ""
    )

    data class Headshot(
        val alt: String = "",
        val href: String = ""
    )

    data class Injury(
        val date: String = "",
        val status: String = ""
    )

    data class Item(
        val age: Int = 0,
        val alternateIds: AlternateIds = AlternateIds(),
        val birthPlace: BirthPlace = BirthPlace(),
        val contracts: List<Any> = listOf(),
        val dateOfBirth: String = "",
        val debutYear: Int = 0,
        val displayHeight: String = "",
        val displayName: String = "",
        val displayWeight: String = "",
        val experience: Experience = Experience(),
        val firstName: String = "",
        val fullName: String = "",
        val guid: String = "",
        val headshot: Headshot = Headshot(),
        val height: Int = 0,
        val id: String = "",
        val injuries: List<Injury> = listOf(),
        val jersey: String = "",
        val lastName: String = "",
        val links: List<Link> = listOf(),
        val position: Position = Position(),
        val shortName: String = "",
        val slug: String = "",
        val status: Status = Status(),
        val uid: String = "",
        val weight: Int = 0
    )

    data class Link(
        val href: String = "",
        val isExternal: Boolean = false,
        val isPremium: Boolean = false,
        val language: String = "",
        val rel: List<String> = listOf(),
        val shortText: String = "",
        val text: String = ""
    )

    data class Parent(
        val abbreviation: String = "",
        val displayName: String = "",
        val id: String = "",
        val leaf: Boolean = false,
        val name: String = ""
    )

    data class Position(
        val abbreviation: String = "",
        val displayName: String = "",
        val id: String = "",
        val leaf: Boolean = false,
        val name: String = "",
        val parent: Parent = Parent()
    )

    data class Season(
        val name: String = "",
        val type: Int = 0,
        val year: Int = 0
    )

    data class Status(
        val abbreviation: String = "",
        val id: String = "",
        val name: String = "",
        val type: String = ""
    )

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
}