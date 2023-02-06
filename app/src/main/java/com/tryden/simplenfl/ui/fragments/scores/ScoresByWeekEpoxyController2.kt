package com.tryden.simplenfl.ui.fragments.scores

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.models.*

class ScoresByWeekEpoxyController2(
    private val onWeekSelected: (String) -> Unit
): TypedEpoxyController<List<UiEvent>>() {

    private val TAG = ScoresByWeekEpoxyController2::class.java.simpleName


    override fun buildModels(data: List<UiEvent>) {
        if (data.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        data.forEach { uiEvent ->
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
            }
        }
    }

}