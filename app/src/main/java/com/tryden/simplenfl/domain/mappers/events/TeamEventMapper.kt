package com.tryden.simplenfl.domain.mappers.events

import com.tryden.simplenfl.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.network.response.teams.models.scores.Competitor

object TeamEventMapper {

    fun buildFrom(competitor: Competitor) : EventEntity.Team {
        return EventEntity.Team(
            name = competitor.team.shortDisplayName,
            logo = competitor.team.logo
        )
    }
}