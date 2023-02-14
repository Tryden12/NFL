package com.tryden.simplenfl.epoxy.controllers.scores

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.epoxy.interfaces.events.EventEntity.*
import com.tryden.simplenfl.epoxy.interfaces.events.EventEpoxyItem
import com.tryden.simplenfl.epoxy.interfaces.events.EventEpoxyItem.*
import com.tryden.simplenfl.epoxy.models.SectionBottomEpoxyModel
import com.tryden.simplenfl.epoxy.models.SectionHeaderCenteredEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresCompletedEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresUpcomingEpoxyModel

class ScoresByWeekEpoxyController: TypedEpoxyController<List<EventEpoxyItem>>() {

    override fun buildModels(items: List<EventEpoxyItem>) {
        if (items.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        var bottomId = 0
        items.forEach { item ->
            when (item) {
                is HeaderItem -> {
                    SectionHeaderCenteredEpoxyModel(sectionHeader = item.header)
                        .id("gameday-${item.header}").addTo(this)
                }
                is EventItem -> {
                    when (item.event) {
                        is Completed -> {
                            ScoresCompletedEpoxyModel(item.event)
                                .id("event-${item.event.id}").addTo(this)
                        }
                        is Upcoming -> {
                            ScoresUpcomingEpoxyModel(item.event)
                                .id("event-${item.event.id}").addTo(this)
                        }
                    }
                }
                is FooterItem -> {
                    SectionBottomEpoxyModel(useSection = true)
                        .id("bottom-${bottomId++}").addTo(this)
                }
            }
        }

//        eventsList.forEach { event->
//            when (event) {
//                is EventEntity.Completed -> {
//                    Log.e(TAG, "buildModels event completed" )
//                    ScoresCompletedEpoxyModel(
//                        logoAway = event.awayTeam.logo.toString(),
//                        logoHome = event.homeTeam.logo.toString(),
//                        teamNameAway = event.awayTeam.name,
//                        teamNameHome = event.homeTeam.name,
//                        pointsAway = event.scoreAway,
//                        pointsHome = event.scoreHome,
//                        datePlayed = event.datePlayed,
//                        headline = event.headline
//                    ).id("event-${event.id}").addTo(this)
//                }
//                is EventEntity.Upcoming -> {
//                    Log.e(TAG, "buildModels event upcoming" )
//                    ScoresUpcomingEpoxyModel(
//                        logoAway = event.awayTeam.logo.toString(),
//                        logoHome = event.homeTeam.logo.toString(),
//                        teamNameAway = event.awayTeam.name,
//                        teamNameHome = event.homeTeam.name,
//                        recordAway = event.recordAway,
//                        recordHome = event.recordHome,
//                        dateScheduled = event.dateScheduled,
//                        gameTime = event.gameTime,
//                        broadcast = event.broadcast,
//                        headline = event.headline
//                    ).id("event-${event.id}").addTo(this)
//                }
//            }
//        }
    }
}