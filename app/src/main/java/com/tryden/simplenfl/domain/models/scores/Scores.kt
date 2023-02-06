package com.tryden.simplenfl.domain.models.scores


data class Scores(
    val events: List<Event> = listOf(),
    val leagues: List<League> = listOf(),
    val season: Season = Season(),
    val week: Week = Week()
) {

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
        val broadcasts: List<Broadcast> = listOf(),
        val competitors: List<Competitor> = listOf(),
        val conferenceCompetition: Boolean = false,
        val date: String = "",
        val headlines: List<Headline>? = listOf(),
        val id: String = "",
        val notes: List<Note> = listOf(),
        val recent: Boolean = false,
        val startDate: String = "",
        val status: Status = Status(),
        val uid: String = "",
    )

    data class Competitor(
        val homeAway: String = "",
        val id: String = "",
        val order: Int = 0,
        val records: List<Record>? = listOf(),
        val score: String = "",
        val team: Team = Team(),
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
        val name: String = "",
        val season: Season = Season(),
        val shortName: String = "",
        val status: StatusX = StatusX(),
        val uid: String = "",
        val week: Week = Week()
    )

    data class Headline(
        val description: String = "",
        val shortLinkText: String = "",
        val type: String = ""
    )

    data class League(
        val abbreviation: String = "",
        val calendar: List<Calendar> = listOf(),
        val id: String = "",
        val logos: List<Logo> = listOf(),
        val name: String = "",
        val uid: String = ""
    )

    data class Link(
        val href: String = "",
        val rel: List<String> = listOf()
    )


    data class Logo(
        val alt: String = "",
        val height: Int = 0,
        val href: String = "",
        val width: Int = 0
    )

    data class Note(
        val headline: String = "",
        val type: String = ""
    )

    data class Record(
        val abbreviation: String? = "",
        val name: String = "",
        val summary: String = "",
        val type: String = ""
    )

    data class Season(
        val type: Int = 0,
        val year: Int = 0
    )

    data class Status(
        val clock: Int = 0,
        val displayClock: String = "",
        val period: Int = 0,
        val type: Type = Type()
    )

    data class StatusX(
        val clock: Int = 0,
        val displayClock: String = "",
        val period: Int = 0,
    )

    data class Team(
        val abbreviation: String = "",
        val alternateColor: String? = "",
        val color: String? = "",
        val displayName: String = "",
        val id: String = "",
        val logo: String? = "",
        val name: String? = "",
        val shortDisplayName: String = "",
        val uid: String = "",
    )

    data class Type(
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
}