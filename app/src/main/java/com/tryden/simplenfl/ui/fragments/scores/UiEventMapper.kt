package com.tryden.simplenfl.ui.fragments.scores

import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.network.response.teams.models.scores.Event
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class UiEventMapper {

    fun buildFrom(event: Event): UiEvent {
        return UiEvent(
            id = event.id,
            date = formatDate(event.date), /** format example: Sun, 1/21 **/
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

    fun formatDate(isoDate: String): String {
        // Parse ISO format to "Sun, 10/18"
        val responseDate = OffsetDateTime
            .parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter = DateTimeFormatter.ofPattern("E',' M/d")

        return responseDate.format(formatter)
    }
}
