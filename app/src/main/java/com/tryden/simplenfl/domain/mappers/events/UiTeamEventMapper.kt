package com.tryden.simplenfl.domain.mappers.events

import com.tryden.simplenfl.domain.interfaces.events.UiEvent
import com.tryden.simplenfl.network.response.teams.models.scores.Competitor

object UiTeamEventMapper {

    fun buildFrom(competitor: Competitor) : UiEvent.Team {
        return UiEvent.Team(
            name = competitor.team.shortDisplayName,
            logo = competitor.team.logo
        )
    }
}