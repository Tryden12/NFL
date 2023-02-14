package com.tryden.simplenfl.epoxy.controllers.scores

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresCompletedEpoxyModel
import com.tryden.simplenfl.epoxy.models.scores.ScoresUpcomingEpoxyModel

class ScoresByWeekEpoxyController(): TypedEpoxyController<List<EventEntity>>() {

    private val TAG = ScoresByWeekEpoxyController::class.java.simpleName


    override fun buildModels(eventsList: List<EventEntity>) {
        if (eventsList.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        Log.e(TAG, "buildModels eventsList size = ${eventsList.size}" )

        eventsList.forEach { event->
            when (event) {
                is EventEntity.Completed -> {
                    Log.e(TAG, "buildModels event completed" )
                    ScoresCompletedEpoxyModel(
                        logoAway = event.awayTeam.logo.toString(),
                        logoHome = event.homeTeam.logo.toString(),
                        teamNameAway = event.awayTeam.name,
                        teamNameHome = event.homeTeam.name,
                        pointsAway = event.scoreAway,
                        pointsHome = event.scoreHome,
                        datePlayed = event.datePlayed,
                        statusDesc = event.statusDesc,
                        headline = event.headline
                    ).id("event-${event.id}").addTo(this)
                }
                is EventEntity.Upcoming -> {
                    Log.e(TAG, "buildModels event upcoming" )
                    ScoresUpcomingEpoxyModel(
                        logoAway = event.awayTeam.logo.toString(),
                        logoHome = event.homeTeam.logo.toString(),
                        teamNameAway = event.awayTeam.name,
                        teamNameHome = event.homeTeam.name,
                        recordAway = event.recordAway,
                        recordHome = event.recordHome,
                        dateScheduled = event.dateScheduled,
                        gameTime = event.gameTime,
                        broadcast = event.broadcast,
                        headline = event.headline
                    ).id("event-${event.id}").addTo(this)
                }
            }
        }
    }
}