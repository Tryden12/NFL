package com.tryden.simplenfl.network.response.teams.models.player

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
)