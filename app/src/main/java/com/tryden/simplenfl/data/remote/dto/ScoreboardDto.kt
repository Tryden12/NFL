package com.tryden.simplenfl.data.remote.dto


data class ScoreboardDto(
    val events: List<Event> = listOf(),
    val leagues: List<League> = listOf(),
    val week: Week = Week()
) {

    data class Address(
        val city: String = "",
        val state: String = ""
    )

    data class Broadcast(
        val market: String = "",
        val names: List<String> = listOf()
    )

    data class Calendar(
        val endDate: String? = "",
        val entries: List<Entry>? = listOf(),
        val label: String? = "",
        val startDate: String? = "",
        val value: String? = ""
    )

    data class Competition(
        val attendance: Int = 0,
        val broadcasts: List<Broadcast> = listOf(),
        val competitors: List<Competitor> = listOf(),
        val date: String = "",
        val geoBroadcasts: List<GeoBroadcast> = listOf(),
        val headlines: List<Headline>? = listOf(),
        val id: String = "",
        val notes: List<Note> = listOf(),
        val odds: List<Odd>? = listOf(),
        val startDate: String = "",
        val status: Status = Status(),
        val venue: Venue? = Venue()
    )

    data class Competitor(
        val homeAway: String = "",
        val id: String = "",
        val records: List<Record> = listOf(),
        val score: String = "",
        val team: Team = Team(),
        val winner: Boolean? = false
    )

    data class Entry(
        val alternateLabel: String = "",
        val detail: String = "",
        val endDate: String = "",
        val label: String = "",
        val startDate: String = "",
        val value: String = ""
    )

    // bookmark
    data class Event(
        val competitions: List<Competition> = listOf(),
        val date: String = "",
        val id: String = "",
        val name: String = "",
        val season: Season = Season(),
        val shortName: String = "",
        val status: Status = Status(),
        val weather: Weather? = Weather(),
        val week: Week = Week()
    )

    data class GeoBroadcast(
        val media: Media = Media(),
    )

    data class Headline(
        val description: String = "",
        val shortLinkText: String = "",
        val type: String = ""
    )

    data class League(
        val abbreviation: String = "",
        val calendar: List<Calendar> = listOf(),
        val calendarEndDate: String = "",
        val calendarStartDate: String = "",
        val id: String = "",
        val logos: List<Logo> = listOf(),
        val name: String = "",
        val season: SeasonLeague = SeasonLeague(),
    )

    data class Logo(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val lastUpdated: String = "",
        val width: Int = 0
    )

    data class Media(
        val shortName: String = ""
    )

    data class Note(
        val headline: String = "",
        val type: String = ""
    )

    data class Odd(
        val details: String = "",
        val overUnder: Double = 0.0,
        val provider: Provider = Provider()
    )

    data class Provider(
        val id: String = "",
        val name: String = "",
        val priority: Int = 0
    )

    data class Record(
        val abbreviation: String? = "",
        val name: String = "",
        val summary: String = "",
        val type: String = ""
    )

    data class Season(
        val slug: String = "",
        val type: Int = 0,
        val year: Int = 0
    )

    data class SeasonLeague(
        val endDate: String = "",
        val startDate: String = "",
        val type: TypeSeason = TypeSeason(),
        val year: Int = 0
    )

    data class Status(
        val clock: Int = 0,
        val displayClock: String = "",
        val period: Int = 0,
        val type: TypeStatus = TypeStatus()
    )

    data class Team(
        val abbreviation: String = "",
        val alternateColor: String? = "",
        val color: String? = "",
        val displayName: String = "",
        val id: String = "",
        val location: String = "",
        val logo: String? = "",
        val name: String? = "",
        val shortDisplayName: String = "",
    )

    data class TypeSeason(
        val abbreviation: String = "",
        val id: String = "",
        val name: String = "",
        val type: Int = 0
    )

    data class TypeStatus(
        val completed: Boolean = false,
        val description: String = "",
        val detail: String = "",
        val id: String = "",
        val name: String = "",
        val shortDetail: String = "",
        val state: String = ""
    )

    data class Week(
        val number: Int = 0
    )

    data class Weather(
        val conditionId: String = "",
        val displayValue: String = "",
        val highTemperature: Int = 0,
        val temperature: Int = 0
    )

    data class Venue(
        val address: Address = Address(),
        val capacity: Int = 0,
        val fullName: String = "",
        val id: String = "",
        val indoor: Boolean = false
    )

}