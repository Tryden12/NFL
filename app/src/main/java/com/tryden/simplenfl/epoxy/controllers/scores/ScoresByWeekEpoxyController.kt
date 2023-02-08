package com.tryden.simplenfl.epoxy.controllers.scores

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.domain.models.scores.events.UiEvent
import com.tryden.simplenfl.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresCompletedEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresUpcomingEpoxyModel
import com.tryden.simplenfl.epoxy.models.SectionBottomEpoxyModel
import com.tryden.simplenfl.epoxy.models.SectionHeaderCenteredEpoxyModel

class ScoresByWeekEpoxyController(): TypedEpoxyController<List<UiEvent>>() {

    private val TAG = ScoresByWeekEpoxyController::class.java.simpleName


    override fun buildModels(uiEvents: List<UiEvent>) {
        if (uiEvents.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }


        var headerFilled = false
        var headerDate = ""
        uiEvents.forEach { uiEvent ->
//            when (uiEvent) {
//                is Event.Completed -> {
//                    ScoresCompletedEpoxyModel(
//                        logoAway = uiEvent.competitions[0].teams[1].team.logo.toString(),
//                        logoHome = uiEvent.competitions[0].teams[0].team.logo.toString(),
//                        teamNameAway = uiEvent.competitions[0].teams[1].team.shortDisplayName,
//                        teamNameHome = uiEvent.competitions[0].teams[0].team.shortDisplayName,
//                        pointsAway = uiEvent.competitions[0].teams[1].score,
//                        pointsHome = uiEvent.competitions[0].teams[0].score,
//                        datePlayed = uiEvent.date,
//                        statusDesc = uiEvent.statusParent.status.description,
//                        headline = ""
//                    ).id("event-${uiEvent.id}").addTo(this)
//                }
//                is Event.Upcoming -> {
//
//                }
//            }

            if (uiEvent.statusParent.status.completed) {
                if (headerDate != uiEvent.date) {
                    // Add bottom to section
                    if (headerFilled) {
                        SectionBottomEpoxyModel(
                            useSection = true
                        ).id("bottom-${uiEvent.date}").addTo(this)
                    }
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = uiEvent.date
                    ).id("header-${uiEvent.date}").addTo(this)
                    headerFilled = true
                    headerDate = uiEvent.date
                }
                ScoresCompletedEpoxyModel(
                        logoAway = uiEvent.competitions[0].teams[1].team.logo.toString(),
                        logoHome = uiEvent.competitions[0].teams[0].team.logo.toString(),
                        teamNameAway = uiEvent.competitions[0].teams[1].team.shortDisplayName,
                        teamNameHome = uiEvent.competitions[0].teams[0].team.shortDisplayName,
                        pointsAway = uiEvent.competitions[0].teams[1].score,
                        pointsHome = uiEvent.competitions[0].teams[0].score,
                        datePlayed = uiEvent.date,
                        statusDesc = uiEvent.statusParent.status.description,
                        headline = uiEvent.competitions[0].notes[0].headline
                ).id("event-${uiEvent.id}").addTo(this)
            } else {
                if (headerDate != uiEvent.date) {
                    // Add bottom to section
                    if (headerFilled) {
                        SectionBottomEpoxyModel(
                            useSection = true
                        ).id("bottom-${uiEvent.date}").addTo(this)
                    }
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = uiEvent.date
                    ).id("header-${uiEvent.date}").addTo(this)
                    headerFilled = true
                    headerDate = uiEvent.date
                }
                ScoresUpcomingEpoxyModel(
                    logoAway = uiEvent.competitions[0].teams[1].team.logo.toString(),
                    logoHome = uiEvent.competitions[0].teams[0].team.logo.toString(),
                    teamNameAway = uiEvent.competitions[0].teams[1].team.shortDisplayName,
                    teamNameHome = uiEvent.competitions[0].teams[0].team.shortDisplayName,
                    recordAway = uiEvent.competitions[0].teams[1].record[0].record,
                    recordHome = uiEvent.competitions[0].teams[0].record[0].record,
                    dateScheduled = uiEvent.date,
                    gameTime = uiEvent.gameTime,
                    broadcast = uiEvent.competitions[0].broadcast[0].shortName,
                    headline = uiEvent.competitions[0].notes[0].headline
                ).id("event-${uiEvent.id}").addTo(this)
            }
        }


    }

}