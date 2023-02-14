package com.tryden.simplenfl.domain.mappers.events

import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity.*
import com.tryden.simplenfl.network.response.teams.models.scores.Event
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


object EventMapper2 {

    fun buildFrom(event: Event): EventEntity {
        return when (event.status.type.completed) {
            true -> { /** Event status = Completed **/
                Completed(
                    id = event.id,
                    homeTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[0]),
                    awayTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[1]),
                    scoreHome = event.competitions[0].competitors[0].score,
                    scoreAway = event.competitions[0].competitors[1].score,
                    datePlayed = formatDate(event.date), /** format example: Sun, 1/21 **/
                    statusDesc = event.status.type.description,
                    headline = if (event.competitions[0].notes.isNotEmpty() && event.competitions[0].notes[0].headline.isNotEmpty()) {
                        event.competitions[0].notes[0].headline
                    } else {
                        "" // leave it empty
                    },
                    seasonType = event.season.slug
                )
            }
            else -> { /** Event status = Upcoming **/
                Upcoming(
                    id = event.id,
                    homeTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[0]),
                    awayTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[1]),
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
                    },
                    seasonType = event.season.slug
                )
            }
        }
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

    fun formatGameTime(isoDate: String): String {
        // Parse ISO format to "Sun, 10/18"
        val responseDate = OffsetDateTime
            .parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter = DateTimeFormatter.ofPattern("h:mm a")

        return responseDate.format(formatter)
    }
}
