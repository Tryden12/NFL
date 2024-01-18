package com.tryden.simplenfl.data.remote.dto

data class RosterDto(
    val athletes: List<Athlete> = listOf(),
    val coach: List<Coach> = listOf(),
    val team: Team = Team(),
) {

    data class Athlete(
        val items: List<Item> = listOf(),
        val position: String = ""
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

    data class Headshot(
        val alt: String = "",
        val href: String = ""
    )

    data class Item(
        val age: Int = 0,
        val displayHeight: String = "",
        val displayName: String = "",
        val displayWeight: String = "",
        val experience: Experience = Experience(),
        val firstName: String = "",
        val fullName: String = "",
        val headshot: Headshot = Headshot(),
        val height: Int = 0,
        val id: String = "",
        val jersey: String = "",
        val lastName: String = "",
        val position: Position = Position(),
        val shortName: String = "",
        val weight: Int = 0
    )

    data class Parent(
        val displayName: String = ""
    )

    data class Position(
        val abbreviation: String = "",
        val displayName: String = "",
        val id: String = "",
        val name: String = "",
        val parent: Parent = Parent()
    )

    data class Team(
        val abbreviation: String = "",
        val clubhouse: String = "",
        val color: String = "",
        val displayName: String = "",
        val id: String = "",
        val location: String = "",
        val logo: String = "",
        val name: String = "",
        val recordSummary: String = "",
        val standingSummary: String = ""
    )

}