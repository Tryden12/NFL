package com.tryden.simplenfl.epoxy.controllers.team.scores

import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.epoxy.controllers.models.*
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse


class TeamScoresEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value){
            field = value
            if (value) {
                requestModelBuild()
            }
        }

    var scoresResponse: ScoreboardResponse? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    // changed via saving to a view model using activity lifecycle
    var onTeamSelected: String? = ""
        set(value) {
            field = value
        }

    override fun buildModels() {
        if (isLoading) {
//            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (scoresResponse == null) {
            // todo error state
            return
        }


        /**
         * Playoffs
         **/
        var headerTopFilled = false
        var scheduledCount = 0
        for (i in scoresResponse!!.events.size - 1 downTo 0) {
            if (scoresResponse!!.events[i].season.slug.contains("post", ignoreCase = true)) {
                if (scoresResponse!!.events[i].competitions[0].status.type.description.contains("scheduled",
                        ignoreCase = true)
                ) {

                    for (j in scoresResponse!!.events[i].competitions[0].competitors.indices) {
                        if (scoresResponse!!.events[i].competitions[0].competitors[j].team.id == onTeamSelected) {

                            // Use header
                            if (scheduledCount == 0) {
                                SectionHeaderCenteredEpoxyModel(
                                    sectionHeader = "Playoffs"
                                ).id("header-playoffs-$i").addTo(this)
                                scheduledCount++
                                headerTopFilled = true
                            }

                            // Add scores upcoming item
                            ScoresScheduledEpoxyModel(
                                    logoAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                                    logoHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                                    teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.name.toString(),
                                    teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.name.toString(),
                                    recordAway = scoresResponse!!.events[i].competitions[0].competitors[0].records?.get(
                                        0)?.summary.toString(),
                                    recordHome = scoresResponse!!.events[i].competitions[0].competitors[1].records?.get(
                                        0)?.summary.toString(),
                                    dateScheduled = scoresResponse!!.events[i].date,
                                    broadcast = scoresResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
                                    headline = scoresResponse!!.events[i].competitions[0].notes[0].headline
                                ).id(scoresResponse!!.events[i].id).addTo(this)
                        }
                    }
                } else if (scoresResponse!!.events[i].competitions[0].status.type.description.contains("final", ignoreCase = true)) {

                    for (j in scoresResponse!!.events[i].competitions[0].competitors.indices) {
                        if (scoresResponse!!.events[i].competitions[0].competitors[j].team.id == onTeamSelected) {

                            // Use header
                            if (scheduledCount == 0) {
                                SectionHeaderCenteredEpoxyModel(
                                    sectionHeader = "Playoffs"
                                ).id("header-playoffs-$i").addTo(this)
                                scheduledCount++
                                headerTopFilled = true
                            }
                            // Add scores final item
                            ScoresFinalEpoxyModel(
                                    logoAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                                    logoHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                                    teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.name.toString(),
                                    teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.name.toString(),
                                    pointsAway = scoresResponse!!.events[i].competitions[0].competitors[0].score,
                                    pointsHome = scoresResponse!!.events[i].competitions[0].competitors[1].score,
                                    datePlayed = scoresResponse!!.events[i].date,
                                    statusDesc = scoresResponse!!.events[i].competitions[0].status.type.description
                                ).id(scoresResponse!!.events[i].id).addTo(this)
                        }
                    }

                }
            }
        }

        if (headerTopFilled) {
            SectionBottomEpoxyModel(
                useSection = true
            ).id("bottom-playoffs-1").addTo(this)
        }

        /**
         * Regular Season
         **/
        headerTopFilled = false
        scheduledCount = 0
        for (i in scoresResponse!!.events.size - 1 downTo 0) {
            if (scoresResponse!!.events[i].season.slug.contains("regular", ignoreCase = true)) {
                if (scoresResponse!!.events[i].competitions[0].status.type.description.contains("scheduled",
                        ignoreCase = true)
                ) {

                    for (j in scoresResponse!!.events[i].competitions[0].competitors.indices) {
                        if (scoresResponse!!.events[i].competitions[0].competitors[j].team.id == onTeamSelected) {

                            // Use header
                            if (scheduledCount == 0) {
                                SectionHeaderCenteredEpoxyModel(
                                    sectionHeader = "Regular Season"
                                ).id("header-regular-season-$i").addTo(this)
                                scheduledCount++
                                headerTopFilled = true
                            }

                            // Add scores upcoming item
                            ScoresScheduledEpoxyModel(
                                logoAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                                logoHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                                teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.name.toString(),
                                teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.name.toString(),
                                recordAway = scoresResponse!!.events[i].competitions[0].competitors[0].records?.get(
                                    0)?.summary.toString(),
                                recordHome = scoresResponse!!.events[i].competitions[0].competitors[1].records?.get(
                                    0)?.summary.toString(),
                                dateScheduled = scoresResponse!!.events[i].date,
                                broadcast = scoresResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
                                headline = scoresResponse!!.events[i].competitions[0].notes[0].headline
                            ).id(scoresResponse!!.events[i].id).addTo(this)
                            scheduledCount++
                            headerTopFilled = true

                        }
                    }
                } else if (scoresResponse!!.events[i].competitions[0].status.type.description.contains(
                        "final",
                        ignoreCase = true)
                ) {

                    for (j in scoresResponse!!.events[i].competitions[0].competitors.indices) {
                        if (scoresResponse!!.events[i].competitions[0].competitors[j].team.id == onTeamSelected) {

                            // Use header
                            if (scheduledCount == 0) {
                                SectionHeaderCenteredEpoxyModel(
                                    sectionHeader = "Regular Season"
                                ).id("header-regular-season-$i").addTo(this)
                                scheduledCount++
                                headerTopFilled = true
                            }

                            // Add scores final item
                            ScoresFinalEpoxyModel(
                                logoAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                                logoHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                                teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.name.toString(),
                                teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.name.toString(),
                                pointsAway = scoresResponse!!.events[i].competitions[0].competitors[0].score,
                                pointsHome = scoresResponse!!.events[i].competitions[0].competitors[1].score,
                                datePlayed = scoresResponse!!.events[i].date,
                                statusDesc = scoresResponse!!.events[i].competitions[0].status.type.description
                            ).id(scoresResponse!!.events[i].id).addTo(this)
                            scheduledCount++
                            headerTopFilled = true

                        }
                    }
                }
            }
        }
        if (headerTopFilled) {
            SectionBottomEpoxyModel(
                useSection = true
            ).id("bottom-regular-season-1").addTo(this)
        }
    }
}