package com.tryden.simplenfl.domain.mappers.team

import com.tryden.simplenfl.domain.models.team.Team
import com.tryden.simplenfl.network.response.models.team.TeamResponse

object TeamMapper {

    fun buildFrom(team: TeamResponse.Team) : Team {
        return Team (
            id = team.id,
            shortName = team.shortDisplayName,
            longName = team.displayName,
            abbreviation = team.abbreviation,
            color = team.color,
            alternateColor = team.alternateColor,
            logo = team.logos[2].href,
            record = team.record.items[0].summary,
            stadium = team.franchise.venue.fullName
        )
    }
}