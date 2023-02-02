package com.tryden.simplenfl.domain.mappers

import com.tryden.simplenfl.domain.models.teams.AllTeams
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeamsResponse

object TeamMapper {

    fun buildFrom(response: AllTeamsResponse) : AllTeams {
        return AllTeams(
            sports = listOf(AllTeams.Sport(
                id = response.sports[0].id,
                uid = response.sports[0].uid,
                name = response.sports[0].name,
                leagues = listOf(AllTeams.League(
                    id = response.sports[0].leagues[0].id,
                    uid = response.sports[0].leagues[0].uid,
                    name = response.sports[0].leagues[0].name,
                    abbreviation = response.sports[0].leagues[0].abbreviation,
                    shortName = response.sports[0].leagues[0].shortName,
                    slug = response.sports[0].leagues[0].slug,
                    teams = listOf(AllTeams.Teams())
                    ))
                )
            )
        )
    }
}