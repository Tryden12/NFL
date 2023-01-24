package com.tryden.simplenfl.domain.models.team

import com.squareup.moshi.Json


data class TeamObject(
    val team: Team = Team()
) {
    data class Team(
        val abbreviation: String = "",
        val alternateColor: String = "",
        val color: String = "",
        val displayName: String = "",
        val franchise: Franchise = Franchise(),
        val id: String = "",
        val isActive: Boolean = false,
        val location: String = "",
        val logos: List<Logo> = listOf(),
        val name: String = "",
        val nextEvent: List<NextEvent> = listOf(),
        val nickname: String = "",
        val record: RecordTeam = RecordTeam(),
        val shortDisplayName: String = "",
        val slug: String = "",
        val standingSummary: String = "",
        val uid: String = ""
    )


    data class Address(
        val city: String = "",
        val state: String = "",
        val zipCode: String = ""
    )

    data class Broadcast(
        val lang: String = "",
        val market: Market = Market(),
        val media: Media = Media(),
        val region: String = "",
        val type: Type = Type()
    )

    data class Competition(
        val attendance: Int = 0,
        @Json(name = "boxscoreAvailable")
        val boxScoreAvailable: Boolean = false,
        val broadcasts: List<Broadcast> = listOf(),
        val competitors: List<Competitor> = listOf(),
        val date: String = "",
        val id: String = "",
        val neutralSite: Boolean = false,
        val notes: List<Note> = listOf(),
        val status: Status = Status(),
        val ticketsAvailable: Boolean = false,
        val timeValid: Boolean = false,
        val venue: Venue = Venue()
    )

    data class Competitor(
        val homeAway: String = "",
        val id: String = "",
        val order: Int = 0,
        val team: Team = Team(),
        val type: String = ""
    )

    data class Franchise(
        val abbreviation: String = "",
        val color: String = "",
        val displayName: String = "",
        val id: String = "",
        val isActive: Boolean = false,
        val location: String = "",
        val name: String = "",
        val nickname: String = "",
        val shortDisplayName: String = "",
        val slug: String = "",
        val uid: String = "",
        val venue: Venue = Venue()
    )

    data class Image(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val rel: List<String> = listOf(),
        val width: Int = 0
    )

    data class Item(
        val description: String? = "",
        val stats: List<Stat> = listOf(),
        val summary: String = "",
        val type: String = ""
    )

    data class Logo(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val lastUpdated: String = "",
        val rel: List<String> = listOf(),
        val width: Int = 0
    )

    data class Market(
        val id: String = "",
        val type: String = ""
    )

    data class Media(
        val shortName: String = ""
    )

    data class NextEvent(
        val competitions: List<Competition> = listOf(),
        val date: String = "",
        val id: String = "",
        val name: String = "",
        val season: Season = Season(),
        val seasonType: SeasonType = SeasonType(),
        val shortName: String = "",
        val timeValid: Boolean = false,
        val week: Week = Week()
    )

    data class Note(
        val headline: String = "",
        val type: String = ""
    )

    data class RecordTeam(
        val items: List<Item> = listOf()
    )

    data class Season(
        val displayName: String = "",
        val year: Int = 0
    )

    data class SeasonType(
        val abbreviation: String = "",
        val id: String = "",
        val name: String = "",
        val type: Int = 0
    )

    data class Stat(
        val name: String = "",
        val value: Double = 0.0
    )

    data class Status(
        val clock: Int = 0,
        val displayClock: String = "",
        val period: Int = 0,
        val type: StatusType = StatusType()
    )

    data class StatusType(
        val completed: Boolean = false,
        val description: String = "",
        val detail: String = "",
        val id: String = "",
        val name: String = "",
        val shortDetail: String = "",
        val state: String = ""
    )

    data class Type(
        val id: String = "",
        val shortName: String = ""
    )

    data class Venue(
        val address: Address = Address(),
        val capacity: Int = 0,
        val fullName: String = "",
        val grass: Boolean = false,
        val id: String = "",
        val images: List<Image> = listOf(),
        val indoor: Boolean = false
    )

    data class Week(
        val number: Int = 0,
        val text: String = ""
    )
}