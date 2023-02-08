package com.tryden.simplenfl.epoxy.controllers.team.scores

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.domain.models.scores.events.UiEvent
import com.tryden.simplenfl.epoxy.models.SectionBottomEpoxyModel
import com.tryden.simplenfl.epoxy.models.SectionHeaderCenteredEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresCompletedEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresUpcomingEpoxyModel

class TeamScoresEpoxyController: TypedEpoxyController<List<UiEvent>>() {

    // changed via saving to a view model using activity lifecycle
    var onTeamSelected: String? = ""
        set(value) {
            field = value
        }


    override fun buildModels(uiEventsList: List<UiEvent>?) {
        if (uiEventsList != null) {
            if (uiEventsList.isEmpty()) {
                LoadingEpoxyModel().id("loading").addTo(this)
            }
        }

        val eventsList = uiEventsList!!.filter { uiEvent ->
            uiEvent.competitions[0].teams[0].team.id == onTeamSelected ||
                    uiEvent.competitions[0].teams[1].team.id == onTeamSelected
        }

        var headerFilled = false
        var headerSeasonType = ""
        for (i in eventsList.size - 1 downTo 0) {
            val uiEvent = eventsList[i]


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
                if (uiEvent.season.slug.contains("regular") &&
                    headerSeasonType != uiEvent.season.slug
                ) {
                    // Add bottom to section
                    if (headerFilled) {
                        SectionBottomEpoxyModel(
                            useSection = true
                        ).id("bottom-${uiEvent.date}").addTo(this)
                    }
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = "Regular Season"
                    ).id("header-${uiEvent.date}").addTo(this)
                    headerFilled = true
                    headerSeasonType = uiEvent.season.slug
                } else if (uiEvent.season.slug.contains("post") &&
                    headerSeasonType != uiEvent.season.slug
                ) {
                    // Add bottom to section
                    if (headerFilled) {
                        SectionBottomEpoxyModel(
                            useSection = true
                        ).id("bottom-${uiEvent.date}").addTo(this)
                    }
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = "Playoffs"
                    ).id("header-${uiEvent.date}").addTo(this)
                    headerFilled = true
                    headerSeasonType = uiEvent.season.slug
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
                if (uiEvent.season.slug.contains("regular") &&
                    headerSeasonType != uiEvent.season.slug
                ) {
                    // Add bottom to section
                    if (headerFilled) {
                        SectionBottomEpoxyModel(
                            useSection = true
                        ).id("bottom-${uiEvent.date}").addTo(this)
                    }
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = "Regular Season"
                    ).id("header-${uiEvent.date}").addTo(this)
                    headerFilled = true
                    headerSeasonType = uiEvent.date
                } else if (uiEvent.season.slug.contains("post") &&
                    headerSeasonType != uiEvent.season.slug
                ) {
                    // Add bottom to section
                    if (headerFilled) {
                        SectionBottomEpoxyModel(
                            useSection = true
                        ).id("bottom-${uiEvent.date}").addTo(this)
                    }
                    SectionHeaderCenteredEpoxyModel(
                        sectionHeader = "Playoffs"
                    ).id("header-${uiEvent.date}").addTo(this)
                    headerFilled = true
                    headerSeasonType = uiEvent.season.slug
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


        // Add bottom to last group of events
        if (headerFilled) {
            SectionBottomEpoxyModel(
                useSection = true
            ).id("bottom-1").addTo(this)
        }
    }
}