package com.tryden.simplenfl.domain.mappers.events

import com.tryden.simplenfl.domain.interfaces.events.UiEvent
import com.tryden.simplenfl.domain.mappers.events.UiEventMapper.formatDate
import com.tryden.simplenfl.domain.mappers.events.UiEventMapper.formatGameTime
import com.tryden.simplenfl.network.response.teams.models.scores.Event


object UiEventMapper2 {

    fun buildFrom(event: Event): UiEvent {
        return when (event.status.type.completed) {
            true -> { /** Event status = Completed **/
                UiEvent.Completed(
                    id = event.id,
                    homeTeam = UiTeamEventMapper.buildFrom(event.competitions[0].competitors[0]),
                    awayTeam = UiTeamEventMapper.buildFrom(event.competitions[0].competitors[1]),
                    scoreHome = event.competitions[0].competitors[0].score,
                    scoreAway = event.competitions[0].competitors[1].score,
                    datePlayed = formatDate(event.date), /** format example: Sun, 1/21 **/
                    statusDesc = event.status.type.description,
                    headline = if (event.competitions[0].notes.isNotEmpty() && event.competitions[0].notes[0].headline.isNotEmpty()) {
                        event.competitions[0].notes[0].headline
                    } else {
                        "" // leave it empty
                    }
                )
            }
            else -> { /** Event status = Upcoming **/
                UiEvent.Upcoming(
                    id = event.id,
                    homeTeam = UiTeamEventMapper.buildFrom(event.competitions[0].competitors[0]),
                    awayTeam = UiTeamEventMapper.buildFrom(event.competitions[0].competitors[1]),
                    recordHome = event.competitions[0].competitors[0].records[0].summary,
                    recordAway = event.competitions[0].competitors[1].records[0].summary,
                    dateScheduled = formatDate(event.date), /** format example: Sun, 1/21 **/
                    gameTime = formatGameTime(event.date), /** format example: 4:30 PM **/
                    broadcast = if (event.competitions[0].geoBroadcasts.isNotEmpty() && event.competitions[0].geoBroadcasts[0].media.shortName.isNotEmpty()) {
                        event.competitions[0].geoBroadcasts[0].media.shortName
                    } else {
                        "" // leave it empty
                    },
                    headline = if (event.competitions[0].notes.isNotEmpty() && event.competitions[0].notes[0].headline.isNotEmpty()) {
                        event.competitions[0].notes[0].headline
                    } else {
                        "" // leave it empty
                    }
                )
            }
        }
    }
}
