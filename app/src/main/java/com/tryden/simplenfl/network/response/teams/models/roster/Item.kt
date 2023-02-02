package com.tryden.simplenfl.network.response.teams.models.roster

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