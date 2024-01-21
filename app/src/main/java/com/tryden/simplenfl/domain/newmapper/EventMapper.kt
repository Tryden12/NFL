package com.tryden.simplenfl.domain.newmapper

import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.data.remote.dto.ScoreboardDto
import com.tryden.simplenfl.domain.mappers.events.TeamEventMapper
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class EventMapper @Inject constructor() : Mapper<EventEntity, ScoreboardDto.Event> {

    override fun buildFrom(event: ScoreboardDto.Event): EventEntity {
        return when (event.status.type.completed) {
            true -> { /** Event status = Completed **/
                EventEntity.Completed(
                    id = event.id,
                    homeTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[0]),
                    awayTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[1]),
                    scoreHome = event.competitions[0].competitors[0].score,
                    scoreAway = event.competitions[0].competitors[1].score,
                    datePlayed = formatDate(event.date),
                    /** format example: Sun, 1/21 **/
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
                EventEntity.Upcoming(
                    id = event.id,
                    homeTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[0]),
                    awayTeam = TeamEventMapper.buildFrom(event.competitions[0].competitors[1]),
                    recordHome = if (event.competitions[0]?.competitors?.get(0)?.records?.size!! > 0) {
                        event.competitions[0]?.competitors?.get(0)?.records?.get(0)?.summary ?: ""
                    } else {
                        "" // leave it empty
                    },
                    recordAway = if (event.competitions[0]?.competitors?.get(1)?.records?.size!! > 0) {
                        event.competitions[0]?.competitors?.get(1)?.records?.get(0)?.summary ?: ""
                    } else {
                        "" // leave it empty
                    },
                    dateScheduled = formatDate(event.date),
                    /** format example: Sun, 1/21 **/
                    gameTime = formatGameTime(event.date),
                    /** format example: 4:30 PM **/
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
        // Parse ISO format to "4:30 PM"
        val responseDate = OffsetDateTime
            .parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter = DateTimeFormatter.ofPattern("h:mm a")

        return responseDate.format(formatter)
    }
}