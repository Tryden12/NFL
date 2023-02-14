package com.tryden.simplenfl.epoxy

import android.util.Log
import com.tryden.simplenfl.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.epoxy.interfaces.events.EventEntity.*
import com.tryden.simplenfl.epoxy.interfaces.events.EventEpoxyItem

class EpoxyDataManager {


    fun giveMeEventEpoxyItems(events: List<EventEntity>) : List<EventEpoxyItem> {
        Log.e("EpoxyDataManager", "eventsList size = ${events?.size}" )

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
                            add(EventEpoxyItem.HeaderItem(date = event.datePlayed))
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
                                headline = event.headline
                        )))
                    }
                    is Upcoming -> {
                        if (headerFilled && dateHeader != event.dateScheduled) {
                            add(EventEpoxyItem.FooterItem)
                        }
                        if (dateHeader != event.dateScheduled) {
                            add(EventEpoxyItem.HeaderItem(date = event.dateScheduled))
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
                                headline = event.headline
                            )))
                    }
                }
            }
            if (headerFilled) add(EventEpoxyItem.FooterItem)
        }
    }
}