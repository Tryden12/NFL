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
        Log.d("EpoxyDataManager", "eventsList size = ${events.size}" )

        return buildList {
            events.groupBy {
                it.date
            }.forEach { mapEntry ->
                add(EventEpoxyItem.HeaderItem(header = mapEntry.key))
                mapEntry.value.forEach { event ->
                    add(EventEpoxyItem.EventItem(event = event))
                }
                add(EventEpoxyItem.FooterItem)
            }
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

        return buildList {
            events.filter { event ->
                event.homeId == onTeamSelected || event.awayId == onTeamSelected
            }.groupBy {
                it.type.replace("-", " ")
            }.forEach { mapEntry ->
                add(EventEpoxyItem.HeaderItem(header = mapEntry.key))
                mapEntry.value.forEach { event ->
                    add(EventEpoxyItem.EventItem(event = event))
                }
                add(EventEpoxyItem.FooterItem)
            }
        }
    }
}