package com.tryden.simplenfl.domain.models.scores



data class Scores(
    val events: List<Event> = listOf(),
    val leagues: List<League> = listOf()
) {

    data class Address(
        val city: String = "",
        val state: String = ""
    )

    data class Athlete(
        val active: Boolean = false,
        val displayName: String = "",
        val fullName: String = "",
        val headshot: String = "",
        val id: String = "",
        val jersey: String = "",
        val links: List<Link> = listOf(),
        val position: Position = Position(),
        val shortName: String = "",
        val team: TeamX = TeamX()
    )

    data class AthleteX(
        val active: Boolean = false,
        val displayName: String = "",
        val fullName: String = "",
        val headshot: String = "",
        val id: String = "",
        val jersey: String = "",
        val shortName: String = "",
        val team: TeamX = TeamX()
    )

    data class Broadcast(
        val market: String = "",
        val names: List<String> = listOf()
    )

    data class Calendar(
        val endDate: String? = "",
        val entries: List<Entry> = listOf(),
        val label: String? = "",
        val startDate: String? = "",
        val value: String? = ""
    )

    data class Competition(
        val attendance: Int = 0,
        val broadcasts: List<Broadcast> = listOf(),
        val competitors: List<Competitor> = listOf(),
        val conferenceCompetition: Boolean = false,
        val date: String = "",
        val format: Format = Format(),
        val geoBroadcasts: List<GeoBroadcast> = listOf(),
        val headlines: List<Headline>? = listOf(),
        val id: String = "",
        val leaders: List<LeaderXX>? = listOf(),
        val neutralSite: Boolean = false,
        val notes: List<Note> = listOf(),
        val odds: List<Odd>? = listOf(),
        val recent: Boolean = false,
        val startDate: String = "",
        val status: Status = Status(),
        val tickets: List<Ticket>? = listOf(),
        val timeValid: Boolean = false,
        val type: TypeXX = TypeXX(),
        val uid: String = "",
        val venue: VenueX? = VenueX()
    )

    data class Competitor(
        val homeAway: String = "",
        val id: String = "",
        val leaders: List<Leader>? = listOf(),
        val linescores: List<Linescore>? = listOf(),
        val order: Int = 0,
        val records: List<Record>? = listOf(),
        val score: String = "",
        val statistics: List<Any> = listOf(),
        val team: TeamXX = TeamXX(),
        val type: String = "",
        val uid: String = "",
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

    data class EntryWeek(
        val label: String,
        val dates: String,
        val number: String,
        val range: String,
    )

    data class Event(
        val competitions: List<Competition> = listOf(),
        val date: String = "",
        val id: String = "",
        val links: List<LinkXXXX> = listOf(),
        val name: String = "",
        val season: Season = Season(),
        val shortName: String = "",
        val status: StatusX = StatusX(),
        val uid: String = "",
        val weather: Weather? = Weather(),
        val week: Week = Week()
    )

    data class Format(
        val regulation: Regulation = Regulation()
    )

    data class GeoBroadcast(
        val lang: String = "",
        val market: Market = Market(),
        val media: Media = Media(),
        val region: String = "",
        val type: Type = Type()
    )

    data class Headline(
        val description: String = "",
        val shortLinkText: String = "",
        val type: String = ""
    )

    data class Leader(
        val abbreviation: String = "",
        val displayName: String = "",
        val leaders: List<LeaderX> = listOf(),
        val name: String = "",
        val shortDisplayName: String = ""
    )

    data class LeaderX(
        val athlete: Athlete = Athlete(),
        val displayValue: String = "",
        val team: TeamX = TeamX(),
        val value: Int = 0
    )

    data class LeaderXX(
        val abbreviation: String = "",
        val displayName: String = "",
        val leaders: List<LeaderXXX> = listOf(),
        val name: String = "",
        val shortDisplayName: String = ""
    )

    data class LeaderXXX(
        val athlete: AthleteX = AthleteX(),
        val displayValue: String = "",
        val team: TeamX = TeamX(),
        val value: Int = 0
    )

    data class League(
        val abbreviation: String = "",
        val calendar: List<Calendar> = listOf(),
        val id: String = "",
        val logos: List<Logo> = listOf(),
        val name: String = "",
        val season: SeasonX = SeasonX(),
        val slug: String = "",
        val uid: String = ""
    )

    data class Linescore(
        val value: Int = 0
    )

    data class Link(
        val href: String = "",
        val rel: List<String> = listOf()
    )

    data class LinkX(
        val href: String = "",
        val isExternal: Boolean = false,
        val isPremium: Boolean = false,
        val rel: List<String> = listOf(),
        val text: String = ""
    )

    data class LinkXXX(
        val href: String = ""
    )

    data class LinkXXXX(
        val href: String = "",
        val isExternal: Boolean = false,
        val isPremium: Boolean = false,
        val language: String = "",
        val rel: List<String> = listOf(),
        val shortText: String = "",
        val text: String = ""
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

    data class Note(
        val headline: String = "",
        val type: String = ""
    )

    data class Odd(
        val details: String = "",
        val overUnder: Double = 0.0,
        val provider: Provider = Provider()
    )

    data class Position(
        val abbreviation: String = ""
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

    data class Regulation(
        val periods: Int = 0
    )

    data class Season(
        val slug: String = "",
        val type: Int = 0,
        val year: Int = 0
    )

    data class SeasonX(
        val endDate: String = "",
        val startDate: String = "",
        val type: TypeXXXX = TypeXXXX(),
        val year: Int = 0
    )

    data class Status(
        val clock: Int = 0,
        val displayClock: String = "",
        val period: Int = 0,
        val type: TypeX = TypeX()
    )

    data class StatusX(
        val clock: Int = 0,
        val displayClock: String = "",
        val period: Int = 0,
//    val type: TypeXXX = TypeXXX()
    )

    data class TeamX(
        val id: String = ""
    )

    data class TeamXX(
        val abbreviation: String = "",
        val alternateColor: String? = "",
        val color: String? = "",
        val displayName: String = "",
        val id: String = "",
        val isActive: Boolean = false,
        val links: List<LinkX> = listOf(),
        val location: String = "",
        val logo: String? = "",
        val name: String? = "",
        val shortDisplayName: String = "",
        val uid: String = "",
        val venue: Venue? = Venue()
    )

    data class Ticket(
        val links: List<LinkXXX> = listOf(),
        val numberAvailable: Int = 0,
        val summary: String = ""
    )

    data class Type(
        val id: String = "",
        val shortName: String = ""
    )

    data class TypeX(
        val completed: Boolean = false,
        val description: String = "",
        val detail: String = "",
        val id: String = "",
        val name: String = "",
        val shortDetail: String = "",
        val state: String = ""
    )

    data class TypeXX(
        val abbreviation: String = "",
        val id: String = ""
    )

    data class TypeXXXX(
        val abbreviation: String = "",
        val id: String = "",
        val name: String = "",
        val type: Int = 0
    )

    data class Venue(
        val id: String = ""
    )

    data class VenueX(
        val address: Address = Address(),
        val capacity: Int = 0,
        val fullName: String = "",
        val id: String = "",
        val indoor: Boolean = false
    )

    data class Weather(
        val conditionId: String = "",
        val displayValue: String = "",
        val highTemperature: Int = 0,
//    val link: LinkXXXXX = LinkXXXXX(),
        val temperature: Int = 0
    )

    data class Week(
        val number: Int = 0
    )
}