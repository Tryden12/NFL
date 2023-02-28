package com.tryden.simplenfl.ui.epoxy.controllers.scores

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.domain.models.scores.events.UiEvent
import com.tryden.simplenfl.ui.epoxy.models.SectionFooterEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderCenteredEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel

class ScoresUpcomingEpoxyController(): TypedEpoxyController<List<UiEvent>>(){

    override fun buildModels(uiEvents: List<UiEvent>) {
        if (uiEvents.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        var headerFilled = false
        for (i in uiEvents.indices) {
            val uiEvent = uiEvents[i]

            if (!uiEvent.statusParent.status.completed) {
                if (!headerFilled) {
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = "Upcoming"
                    ).id("header-${uiEvent.date}").addTo(this)
                    headerFilled = true
                }
//
//                ScoresUpcomingEpoxyModel(
//                    logoAway = uiEvent.competitions[0].teams[1].team.logo.toString(),
//                    logoHome = uiEvent.competitions[0].teams[0].team.logo.toString(),
//                    teamNameAway = uiEvent.competitions[0].teams[1].team.shortDisplayName,
//                    teamNameHome = uiEvent.competitions[0].teams[0].team.shortDisplayName,
//                    recordAway = uiEvent.competitions[0].teams[1].record[0].record,
//                    recordHome = uiEvent.competitions[0].teams[0].record[0].record,
//                    dateScheduled = uiEvent.date,
//                    gameTime = uiEvent.gameTime,
//                    broadcast = uiEvent.competitions[0].broadcast[0].shortName,
//                    headline = uiEvent.competitions[0].notes[0].headline
//                ).id("event-${uiEvent.id}").addTo(this)

            }
        }

        // Add bottom to last group of events
        if (headerFilled) {
            SectionFooterEpoxyModel(
                useSection = true
            ).id("bottom-1").addTo(this)
        }
    }
}