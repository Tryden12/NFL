package com.tryden.simplenfl.domain.mappers

import com.tryden.simplenfl.domain.models.roster.Roster
import com.tryden.simplenfl.network.response.models.roster.RosterResponse

object RosterMapper {

    fun buildFrom(response: com.tryden.simplenfl.network.response.models.roster.RosterResponse): Roster {
        return Roster(
            season = Roster.Season(
                name = response.season.name,
                type = response.season.type,
                year = response.season.year
            ),
            coach = listOf(Roster.Coach(
                id = response.coach[0].id,
                firstName = response.coach[0].firstName,
                lastName = response.coach[0].lastName,
                experience = response.coach[0].experience
            )),
            athletes = listOf(Roster.Athlete())
        )
    }
}