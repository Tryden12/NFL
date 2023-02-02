package com.tryden.simplenfl.domain.models.player


data class Player(
    val `$ref`: String = "",
    val active: Boolean = false,
    val age: Int = 0,
    val alternateIds: AlternateIds = AlternateIds(),
    val birthPlace: BirthPlace = BirthPlace(),
    val college: College = College(),
    val collegeAthlete: CollegeAthlete = CollegeAthlete(),
    val contracts: Contracts = Contracts(),
    val dateOfBirth: String = "",
    val debutYear: Int = 0,
    val displayHeight: String = "",
    val displayName: String = "",
    val displayWeight: String = "",
    val draft: Draft = Draft(),
    val experience: Experience = Experience(),
    val firstName: String = "",
    val fullName: String = "",
    val guid: String = "",
    val headshot: Headshot = Headshot(),
    val height: Int = 0,
    val id: String = "",
    val jersey: String = "",
    val lastName: String = "",
    val linked: Boolean = false,
    val links: List<Link> = listOf(),
    val position: Position = Position(),
    val shortName: String = "",
    val slug: String = "",
    val statistics: Statistics = Statistics(),
    val statisticslog: Statisticslog = Statisticslog(),
    val status: Status = Status(),
    val team: TeamDraft = TeamDraft(),
    val type: String = "",
    val uid: String = "",
    val weight: Int = 0
) {

    data class AlternateIds(
        val sdr: String = ""
    )

    data class BirthPlace(
        val city: String = "",
        val country: String = "",
        val state: String = ""
    )

    data class College(
        val `$ref`: String = ""
    )

    data class CollegeAthlete(
        val `$ref`: String = ""
    )

    data class Contracts(
        val `$ref`: String = ""
    )

    data class Draft(
        val displayText: String = "",
        val pick: Pick = Pick(),
        val round: Int = 0,
        val selection: Int = 0,
        val team: TeamDraft = TeamDraft(),
        val year: Int = 0
    )

    data class Experience(
        val years: Int = 0
    )

    data class Headshot(
        val alt: String = "",
        val href: String = ""
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
        val `$ref`: String = ""
    )

    data class Pick(
        val `$ref`: String = ""
    )

    data class Position(
        val `$ref`: String = "",
        val abbreviation: String = "",
        val displayName: String = "",
        val id: String = "",
        val leaf: Boolean = false,
        val name: String = "",
        val parent: Parent = Parent()
    )

    data class Statistics(
        val `$ref`: String = ""
    )

    data class Statisticslog(
        val `$ref`: String = ""
    )

    data class Status(
        val abbreviation: String = "",
        val id: String = "",
        val name: String = "",
        val type: String = ""
    )

    data class TeamDraft(
        val `$ref`: String = ""
    )


}