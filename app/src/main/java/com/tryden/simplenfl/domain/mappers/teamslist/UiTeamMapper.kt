package com.tryden.simplenfl.domain.mappers.teamslist

import com.tryden.simplenfl.data.remote.dto.AllTeamsDto.Team
import com.tryden.simplenfl.domain.models.teamslist.UiTeam

class UiTeamMapper {
    fun buildFrom(team: Team): UiTeam {
        return UiTeam(
            id = team.id,
            imageUrl = team.logos[0].href,
            name = team.shortDisplayName
        )
    }
}