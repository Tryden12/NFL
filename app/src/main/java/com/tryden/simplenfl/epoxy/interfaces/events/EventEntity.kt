package com.tryden.simplenfl.epoxy.interfaces.events

sealed interface EventEntity {

    data class Completed(
        val id: String,
        val homeTeam: Team,
        val awayTeam: Team,
        val scoreHome: String,
        val scoreAway: String,
        val datePlayed: String,
        val statusDesc: String,
        val headline: String,
        val seasonType: String
        ): EventEntity

    data class Upcoming(
        val id: String,
        val homeTeam: Team,
        val awayTeam: Team,
        val recordHome: String,
        val recordAway: String,
        val dateScheduled: String,
        val gameTime: String,
        val broadcast: String,
        val headline: String,
        val seasonType: String
        ): EventEntity

    data class Team(
        val id: String,
        val logo: String?,
        val name: String
    )

}