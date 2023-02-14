package com.tryden.simplenfl.domain.mappers.events

import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity.Team
import com.tryden.simplenfl.network.response.teams.models.scores.Competitor

object TeamEventMapper {

    fun buildFrom(competitor: Competitor) : Team {
        return Team(
            id = competitor.team.id,
            name = competitor.team.shortDisplayName,
            logo = competitor.team.logo
        )
    }
}