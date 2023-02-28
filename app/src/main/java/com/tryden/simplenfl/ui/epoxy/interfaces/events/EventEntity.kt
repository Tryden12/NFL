package com.tryden.simplenfl.ui.epoxy.interfaces.events

sealed class EventEntity(
    val homeId: String,
    val awayId: String,
    val date: String,
    val type: String
) {

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
        ): EventEntity(homeTeam.id, awayTeam.id, datePlayed, seasonType)

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
    ): EventEntity(homeTeam.id, awayTeam.id, dateScheduled, seasonType)

    data class Team(
        val id: String,
        val logo: String?,
        val name: String
    )

}