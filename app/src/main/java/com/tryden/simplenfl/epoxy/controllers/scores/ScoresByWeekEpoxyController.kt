package com.tryden.simplenfl.epoxy.controllers.scores

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelScoresFinalWithHeaderItemBinding
import com.tryden.simplenfl.databinding.ModelScoresScheduledWithHeaderItemBinding
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.models.*
import com.tryden.simplenfl.network.response.teams.models.scores.Event
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import java.time.OffsetDateTime
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




    fun convertIsoDateToHeaderTitle(isoDate: String): String {
        // Parse ISO format to "Sun, 10/18"
        val responseDate = OffsetDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
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

                // Use header
                if (eventCount == 0) {
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = convertIsoDateToHeaderTitle(scoresByWeekResponse!!.events[i].date)
                    ).id("header-$i").addTo(this)
                    headerTopFilled = true
                }

                if (events[i].competitions[0].status.type.completed) {
                    // Add scores final item
                    ScoresFinalEpoxyModel(
                        logoAway = events[i].competitions[0].competitors[1].team.logo.toString(),
                        logoHome = events[i].competitions[0].competitors[0].team.logo.toString(),
                        teamNameAway =events[i].competitions[0].competitors[1].team.shortDisplayName,
                        teamNameHome = events[i].competitions[0].competitors[0].team.shortDisplayName,
                        pointsAway = events[i].competitions[0].competitors[1].score,
                        pointsHome = events[i].competitions[0].competitors[0].score,
                        datePlayed = events[i].date,
                        statusDesc = events[i].competitions[0].status.type.description,
                        headline = ""
                    ).id(events[i].id).addTo(this)
                    eventCount++
                } else {
                    if (events[i].competitions[0].notes.isNotEmpty()
                        && events[i].competitions[0].notes[0].headline.contains("pro bowl", ignoreCase = true) ) {

                        // Add pro bowl
                        ScoresScheduledEpoxyModel(
                            logoAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            logoHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            teamNameAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
                            teamNameHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
                            recordAway = "",
                            recordHome = "",
                            dateScheduled = scoresByWeekResponse!!.events[i].date,
                            broadcast = scoresByWeekResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
                            headline = scoresByWeekResponse!!.events[i].competitions[0].notes[0].headline
                        ).id(events[i].id).addTo(this)
                    } else {
                        ScoresScheduledEpoxyModel(
                            logoAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            logoHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            teamNameAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
                            teamNameHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
                            recordAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].records?.get(0)?.summary.toString(),
                            recordHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].records?.get(0)?.summary.toString(),
                            dateScheduled = scoresByWeekResponse!!.events[i].date,
                            broadcast = scoresByWeekResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
                            headline = scoresByWeekResponse!!.events[i].competitions[0].notes[0].headline
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



        /**
         * Scores status: Scheduled
         **/
//        var headerTopFilled = false
//        var scheduledCount = 0
////        for (i in scoresByWeekResponse!!.events.size-1 downTo 0) { // reverse
//        for (i in scoresByWeekResponse!!.events.indices) {
//
//            when (scoresByWeekResponse!!.events[i].competitions[0].status.type.completed) {
//                false -> {
//                    if (scoresByWeekResponse!!.events[i].competitions[0].notes[0].headline.contains("pro bowl", ignoreCase = true)
//                    ) {
//
////                        val date = scoresByWeekResponse!!.events[i].date
////                        val headerDate = getWeekRange(date)
//
//                        // Use header
//                        if (scheduledCount == 0) {
//                            SectionHeaderCenteredEpoxyModel(
//                                sectionHeader = convertIsoDateToHeaderTitle(scoresByWeekResponse!!.events[i].date)
//                            ).id("header-playoffs-$i").addTo(this)
//                            headerTopFilled = true
//                        }
//
//                        // Add scores upcoming item
//                        ScoresScheduledEpoxyModel(
//                            logoAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
//                            logoHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
//                            teamNameAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
//                            teamNameHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
//                            recordAway = "",
//                            recordHome = "",
//                            dateScheduled = scoresByWeekResponse!!.events[i].date,
//                            broadcast = scoresByWeekResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
//                            headline = scoresByWeekResponse!!.events[i].competitions[0].notes[0].headline
//                        ).id(scoresByWeekResponse!!.events[i].id).addTo(this)
//                        scheduledCount++
//
//                    } else {
//
//                        // Use header
//                        if (scheduledCount == 0) {
//                            SectionHeaderCenteredEpoxyModel(
//                                sectionHeader = convertIsoDateToHeaderTitle(scoresByWeekResponse!!.events[i].date)
//                            ).id("header-playoffs-$i").addTo(this)
//                            headerTopFilled = true
//                        }
//
//                        // Add scores upcoming item
//                        ScoresScheduledEpoxyModel(
//                            logoAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
//                            logoHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
//                            teamNameAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
//                            teamNameHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
//                            recordAway = scoresByWeekResponse!!.events[i].competitions[0].competitors[1].records?.get(0)?.summary.toString(),
//                            recordHome = scoresByWeekResponse!!.events[i].competitions[0].competitors[0].records?.get(0)?.summary.toString(),
//                            dateScheduled = scoresByWeekResponse!!.events[i].date,
//                            broadcast = scoresByWeekResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
//                            headline = scoresByWeekResponse!!.events[i].competitions[0].notes[0].headline
//                        ).id(scoresByWeekResponse!!.events[i].id).addTo(this)
//                        scheduledCount++
//                    }
//                }
//                else -> {
//                    // nothing to do
//                }
//            }
//        }
//
//        // Add bottom to section
//        if (headerTopFilled) {
//            SectionBottomEpoxyModel(
//                useSection = true
//            ).id("bottom-home-upcoming-1").addTo(this)
//        }
//
//
//        /**
//         * Scores status: final
//         **/
//        headerTopFilled = false
//        scheduledCount = 0
////        for (i in scoresByWeekResponse!!.events.size-1 downTo 0) { // reverse
//        for (i in scoresByWeekResponse!!.events.indices) {
//
//
//        }
//
//        // Add bottom to section
//        if (headerTopFilled) {
//            SectionBottomEpoxyModel(
//                useSection = true
//            ).id("bottom-home-upcoming-1").addTo(this)
//        }
        
    }


}