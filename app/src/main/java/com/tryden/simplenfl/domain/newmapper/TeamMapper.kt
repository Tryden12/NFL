package com.tryden.simplenfl.domain.newmapper

import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.domain.models.team.Team
import javax.inject.Inject

class TeamMapper @Inject constructor() : Mapper<Team, TeamDto.Team> {
    override fun buildFrom(value: TeamDto.Team): Team {
        return Team (
            id = value.id,
            shortName = value.shortDisplayName,
            longName = value.displayName,
            abbreviation = value.abbreviation,
            color = value.color,
            alternateColor = value.alternateColor,
            logo = value.logos[2].href,
            record = value.record.items[0].summary,
            stadium = value.franchise.venue.fullName
        )
    }
}