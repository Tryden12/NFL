package com.tryden.simplenfl.ui.fragments.scores

import com.tryden.simplenfl.network.response.teams.models.scores.Event

class UiEventMapper {

    fun buildFrom(event: Event): UiEvent {
        return UiEvent(
            id = event.id,
            date = event.date,
            week = event.week.number,
            statusParent = UiEvent.UiStatusParent(
                status = UiEvent.UiEventStatus(
                    completed = event.status.type.completed,
                    description = event.status.type.description
                )
            ),
            competitions = (listOf(UiEvent.UiCompetition(
                teams = event.competitions[0].competitors.map { competitor ->
                    UiCompetitorMapper().buildFrom(competitor)
                },
                broadcast = listOf(UiEvent.UiBroadcast(
                    shortName = event.competitions[0].geoBroadcasts[0].media.shortName
                )),
                notes = listOf(UiEvent.UiNote(
                    headline = if (event.competitions[0].notes.isNotEmpty() && event.competitions[0].notes[0].headline.isNotEmpty()) {
                        event.competitions[0].notes[0].headline
                    } else {
                        ""
                    }
                ))
            )))
        )
    }
}
