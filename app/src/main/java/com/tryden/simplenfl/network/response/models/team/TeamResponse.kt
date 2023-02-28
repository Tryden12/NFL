package com.tryden.simplenfl.network.response.models.team

data class TeamResponse(
    val team: Team = Team()
) {
    data class Team(
        val abbreviation: String = "",
        val alternateColor: String = "",
        val color: String = "",
        val displayName: String = "",
        val franchise: Franchise = Franchise(),
        val id: String = "",
        val location: String = "",
        val logos: List<Logo> = listOf(),
        val name: String = "",
        val nickname: String = "",
        val record: RecordTeam = RecordTeam(),
        val shortDisplayName: String = "",
        val standingSummary: String = "",
    )

    data class Franchise(
        val venue: Venue = Venue()
    )

    data class Item(
        val description: String? = "",
        val summary: String = "",
        val type: String = ""
    )

    data class Image(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val rel: List<String> = listOf(),
        val width: Int = 0
    )

    data class Logo(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val lastUpdated: String = "",
        val rel: List<String> = listOf(),
        val width: Int = 0
    )

    data class RecordTeam(
        val items: List<Item> = listOf()
    )


    data class Venue(
        val capacity: Int = 0,
        val fullName: String = "",
        val grass: Boolean = false,
        val id: String = "",
        val images: List<Image> = listOf(),
        val indoor: Boolean = false
    )


}