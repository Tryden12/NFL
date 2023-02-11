package com.tryden.simplenfl.domain.interfaces.events

sealed interface UiEvent {

    data class Date(
        val date: String
    )

    data class Completed(
        val id: String,
        val homeTeam: Team,
        val awayTeam: Team,
        val scoreHome: String,
        val scoreAway: String,
        val datePlayed: String,
        val statusDesc: String,
        val headline: String
    ): UiEvent

    data class Upcoming(
        val id: String,
        val homeTeam: Team,
        val awayTeam: Team,
        val recordHome: String,
        val recordAway: String,
        val dateScheduled: String,
        val gameTime: String,
        val broadcast: String,
        val headline: String
    ): UiEvent

    data class Team(
        val logo: String?,
        val name: String
    )

}