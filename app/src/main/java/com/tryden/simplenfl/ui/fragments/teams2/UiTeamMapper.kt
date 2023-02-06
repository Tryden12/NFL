package com.tryden.simplenfl.ui.fragments.teams2

import com.tryden.simplenfl.network.response.teams.models.teams.Team

class UiTeamMapper {
    fun buildFrom(team: Team): UiTeam {
        return UiTeam(
            id = team.id.toInt(),
            imageUrl = team.logos[0].href,
            name = team.shortDisplayName
        )
    }
}