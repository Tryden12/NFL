package com.tryden.simplenfl.ui.epoxy

import android.util.Log
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity.*
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEpoxyItem

class EpoxyDataManager {

    /**
     * This is the global variable for team id of the currently selected team.
     *
     * Updates from Fragment by observing the onTeamSelectedLiveData in
     * the SharedViewModel.
     */
    var onTeamSelected: String? = ""
        set(value) {
            field = value
        }


    /**
     * This method provides the epoxy items for ScoresByWeekEpoxyController.
     *
     * @return the list of epoxy items for grouped by game day with header and footer.
     * Header examples: "THU, 9/8, SAT 1/21"
     */
    fun giveMeScoresByWeekEpoxyItems(events: List<EventEntity>) : List<EventEpoxyItem> {
        Log.e("EpoxyDataManager", "eventsList size = ${events.size}" )

        var headerFilled = false
        var dateHeader = ""
        return buildList {
            events.forEach { event ->
                when (event) {
                    is Completed -> {
                        if (headerFilled && dateHeader != event.datePlayed) {
                            add(EventEpoxyItem.FooterItem)
                        }
                        if (dateHeader != event.datePlayed) {
                            add(EventEpoxyItem.HeaderItem(header = event.datePlayed))
                            dateHeader = event.datePlayed
                            headerFilled = true
                        }
                        add(EventEpoxyItem.EventItem(
                            event = Completed(
                                id = event.id,
                                homeTeam = event.homeTeam,
                                awayTeam = event.awayTeam,
                                scoreHome = event.scoreHome,
                                scoreAway = event.scoreAway,
                                datePlayed = event.datePlayed,
                                statusDesc = event.statusDesc,
                                headline = event.headline,
                                seasonType = event.seasonType
                            )))
                    }
                    is Upcoming -> {
                        if (headerFilled && dateHeader != event.dateScheduled) {
                            add(EventEpoxyItem.FooterItem)
                        }
                        if (dateHeader != event.dateScheduled) {
                            add(EventEpoxyItem.HeaderItem(header = event.dateScheduled))
                            dateHeader = event.dateScheduled
                            headerFilled = true
                        }
                        add(EventEpoxyItem.EventItem(
                            event = Upcoming(
                                id = event.id,
                                homeTeam = event.homeTeam,
                                awayTeam = event.awayTeam,
                                recordHome = event.recordHome,
                                recordAway = event.recordAway,
                                dateScheduled = event.dateScheduled,
                                gameTime = event.gameTime,
                                broadcast = event.broadcast,
                                headline = event.headline,
                                seasonType = event.seasonType
                            )))
                    }
                }
            }
            if (headerFilled) add(EventEpoxyItem.FooterItem)
        }
    }

    /**
     * This method provides the epoxy items for TeamScoresEpoxyController.
     *
     * @return the list of epoxy items for grouped by season type with header and footer.
     * season type examples:
     * Preseason, Regular Season, Postseason
     */
    fun giveMeScoresBySeasonTypeEpoxyItems(events: List<EventEntity>) : List<EventEpoxyItem> {
        Log.e("EpoxyDataManager", "eventsList size = ${events.size}" )

        var headerFilled = false
        var seasonType = ""
        return buildList {
            events.forEach { event ->
                when (event) {
                    is Completed -> {
                        if (headerFilled && seasonType != event.seasonType) {
                            add(EventEpoxyItem.FooterItem)
                        }
                        if (seasonType != event.seasonType) {
                            val header = event.seasonType
                            add(EventEpoxyItem.HeaderItem(header = header.replace("-", " ")))
                            seasonType = event.seasonType
                            headerFilled = true
                        }
                        if (event.awayTeam.id == onTeamSelected || event.homeTeam.id == onTeamSelected) {
                            add(EventEpoxyItem.EventItem(
                                event = Completed(
                                    id = event.id,
                                    homeTeam = event.homeTeam,
                                    awayTeam = event.awayTeam,
                                    scoreHome = event.scoreHome,
                                    scoreAway = event.scoreAway,
                                    datePlayed = event.datePlayed,
                                    statusDesc = event.statusDesc,
                                    headline = event.headline,
                                    seasonType = event.seasonType
                                )))
                        }

                    }
                    is Upcoming -> {
                        if (headerFilled && seasonType != event.seasonType) {
                            add(EventEpoxyItem.FooterItem)
                        }
                        if (seasonType != event.seasonType) {
                            val header = event.seasonType
                            add(EventEpoxyItem.HeaderItem(header = header.replace("-", " ")))
                            seasonType = event.seasonType
                            headerFilled = true
                        }
                        add(EventEpoxyItem.EventItem(
                            event = Upcoming(
                                id = event.id,
                                homeTeam = event.homeTeam,
                                awayTeam = event.awayTeam,
                                recordHome = event.recordHome,
                                recordAway = event.recordAway,
                                dateScheduled = event.dateScheduled,
                                gameTime = event.gameTime,
                                broadcast = event.broadcast,
                                headline = event.headline,
                                seasonType = event.seasonType
                            )))
                    }
                }
            }
            if (headerFilled) add(EventEpoxyItem.FooterItem)
        }
    }
}