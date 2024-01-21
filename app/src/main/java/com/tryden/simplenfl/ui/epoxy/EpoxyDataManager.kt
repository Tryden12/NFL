package com.tryden.simplenfl.ui.epoxy

import android.util.Log
import com.tryden.simplenfl.data.remote.dto.TeamDto
import com.tryden.simplenfl.domain.models.news.FavoriteHeadline
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEpoxyItem
import com.tryden.simplenfl.ui.epoxy.interfaces.news.FavoritesHeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.interfaces.news.HeadlinesEpoxyItem
import com.tryden.simplenfl.util.Constants.MY_NEWS
import com.tryden.simplenfl.util.Constants.TOP_HEADLINES

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
     * This is the global variable for team details of the currently selected team.
     *
     * Updates from Fragment by observing the teamByIdLiveData in the TeamViewModel.
     */
    var teamDetails: TeamDto.Team? = null
        set(value) {
            field = value
        }

    /**
     * Provides news headlines epoxy items.
     */
    fun giveMeNewsHeadlines(headlines: List<Headline>) : List<HeadlinesEpoxyItem> {
        // create epoxy items list
        return buildList {
            add(HeadlinesEpoxyItem.HeaderItem(headerTitle = TOP_HEADLINES))
            headlines.forEach {
                add(HeadlinesEpoxyItem.HeadlineItem(headline = it))
            }
            add(HeadlinesEpoxyItem.FooterItem)
        }
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
     * Provides the epoxy items for scores around the league and team.
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

    /**
     * Provides epoxy items for favorite team(s) news.
     */
    fun giveMeFavoriteTeamsNewsEpoxyItems(
        list: List<FavoriteHeadline>
    ) : List<FavoritesHeadlinesEpoxyItem> {
        // build epoxy items
        return if (list.isNotEmpty()) {
            buildList {
                add(FavoritesHeadlinesEpoxyItem.HeaderItem(headerTitle = MY_NEWS))
                list
                    .sortedByDescending { it.timeSincePosted }
                    .forEach {
                        add(FavoritesHeadlinesEpoxyItem.FavoriteHeadlineItem(it))
                    }
                add(FavoritesHeadlinesEpoxyItem.FooterItem)
            }
        } else {
            emptyList()
        }
    }
}