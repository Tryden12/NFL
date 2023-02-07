package com.tryden.simplenfl.ui.fragments.scores

import com.tryden.simplenfl.network.response.teams.models.scores.Competitor

class UiCompetitorMapper {

    fun buildFrom(competitors: Competitor) : UiEvent.UiCompetitor {
        return UiEvent.UiCompetitor(
            homeAway = competitors.homeAway,
            winner = competitors.winner,
            score = competitors.score,
            team = UiEvent.UiEventTeam(
                id = competitors.team.id,
                shortDisplayName = competitors.team.shortDisplayName,
                abbreviation = competitors.team.abbreviation,
                logo = competitors.team.logo
            ),
            record = listOf(UiEvent.UiRecord(
                name = competitors.records!![0].name,
                type = competitors.records[0].type,
                record = competitors.records[0].summary
            ))
        )
    }
}