package com.tryden.simplenfl.epoxy.controllers.scores

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.models.*
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ScoresByWeekEpoxyController(
    private val onWeekSelected: (String) -> Unit
): EpoxyController() {

    private val TAG = ScoresByWeekEpoxyController::class.java.simpleName

    var isLoading: Boolean = true
        set(value){
            field = value
            if (value) {
                requestModelBuild()
            }
        }

    var scoresByWeekResponse: ScoreboardResponse? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }


    private fun getDay(isoDate: String): String {
        // Parse ISO format to "Sun, 10/18"
        val responseDate = OffsetDateTime
            .parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter = DateTimeFormatter.ofPattern("E',' M/d")

        return responseDate.format(formatter)
    }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (scoresByWeekResponse == null) {
            // todo error state
            return
        }

        /**
         * Group scores by day
         **/
        var headerTopFilled = false
        var eventCount = 0
        var events = scoresByWeekResponse!!.events
        if (events.isNotEmpty()) {
            for (i in events.indices) {

                Log.e(TAG, "event day = ${getDay(events[i].date)}" )

                if (i == 0) {
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = getDay(scoresByWeekResponse!!.events[i].date)
                    ).id("header-$i").addTo(this)
                    headerTopFilled = true
                } else {
                    if (getDay(events[i].date) != getDay(events[i-1].date)) {
                        // Add bottom to section
                        if (headerTopFilled) {
                            SectionBottomEpoxyModel(
                                useSection = true
                            ).id("bottom-$i").addTo(this)
                        }

                        SectionHeaderCenteredEpoxyModel(
                            sectionHeader = getDay(scoresByWeekResponse!!.events[i].date)
                        ).id("header-$i").addTo(this)
                        headerTopFilled = true
                    }
                }
                var headline = if (events[i].competitions[0].notes.isNotEmpty() && events[i].competitions[0].notes[0].headline.isNotEmpty()) {
                    events[i].competitions[0].notes[0].headline
                } else {
                    ""
                }

                var broadcast = if (events[i].competitions[0].broadcasts.isNotEmpty()) {
                    events[i].competitions[0].broadcasts[0].names[0]
                } else {
                    ""
                }

                if (events[i].competitions[0].status.type.completed) {
                    // Add scores final item
                    ScoresCompletedEpoxyModel(
                        logoAway = events[i].competitions[0].competitors[1].team.logo.toString(),
                        logoHome = events[i].competitions[0].competitors[0].team.logo.toString(),
                        teamNameAway =events[i].competitions[0].competitors[1].team.shortDisplayName,
                        teamNameHome = events[i].competitions[0].competitors[0].team.shortDisplayName,
                        pointsAway = events[i].competitions[0].competitors[1].score,
                        pointsHome = events[i].competitions[0].competitors[0].score,
                        datePlayed = events[i].date,
                        statusDesc = events[i].competitions[0].status.type.description,
                        headline = headline
                    ).id(events[i].id).addTo(this)
                    eventCount++
                } else {
                    if (events[i].competitions[0].notes.isNotEmpty()
                        && events[i].competitions[0].notes[0].headline.contains("pro bowl", ignoreCase = true) ) {

                        // Add pro bowl
                        ScoresUpcomingEpoxyModel(
                            logoAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            logoHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            teamNameAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
                            teamNameHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
                            recordAway = "",
                            recordHome = "",
                            dateScheduled = events[i].date,
                            broadcast = broadcast,
                            headline = headline
                        ).id(events[i].id).addTo(this)
                    } else {
                        ScoresUpcomingEpoxyModel(
                            logoAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            logoHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            teamNameAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
                            teamNameHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
                            recordAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].records?.get(0)?.summary.toString(),
                            recordHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].records?.get(0)?.summary.toString(),
                            dateScheduled = events[i].date,
                            broadcast = broadcast,
                            headline = headline
                        ).id(events[i].id).addTo(this)
                    }
                }

            }
        }

        // Add bottom to section
        if (headerTopFilled) {
            SectionBottomEpoxyModel(
                useSection = true
            ).id("bottom-1").addTo(this)
        }

    }


}