package com.tryden.simplenfl.domain.mappers

import com.tryden.simplenfl.domain.models.scores.Scores
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse

object ScoresMapper {

    fun buildFrom(response: ScoreboardResponse) : Scores{
        return Scores(
            events = listOf(Scores.Event(
                competitions = listOf(Scores.Competition(
                    broadcasts = listOf(Scores.Broadcast(
                        market = "" // todo mapper buildFrom?
                    ))
                ))
            )),
            leagues = emptyList(), // todo
            season = Scores.Season(
                type = response.season.type,
                year = response.season.year
            ),
            week = Scores.Week(
                number = response.week.number
            )
        )
    }
}