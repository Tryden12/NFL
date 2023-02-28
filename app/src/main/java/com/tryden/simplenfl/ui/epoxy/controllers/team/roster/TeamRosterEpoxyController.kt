package com.tryden.simplenfl.ui.epoxy.controllers.team.roster

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.interfaces.team.RosterEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.SpacerRosterItemEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.team.RosterHeaderEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.team.RosterPlayerItemEpoxyModel
import com.tryden.simplenfl.ui.models.RosterViewState

class TeamRosterEpoxyController(
    val onSelectedSort: (RosterViewState.Sort) -> Unit
): TypedEpoxyController<RosterViewState>() {

    override fun buildModels(viewState: RosterViewState) {
        if (viewState.isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }
        if (viewState.dataList.isEmpty()) {
            // todo: add empty state
            return
        }

        viewState.dataList.forEachIndexed { index, dataItem ->
            when (dataItem) {
                is RosterEpoxyItem.HeaderItem -> {
                    RosterHeaderEpoxyModel(rosterType = dataItem.header, onSelectedSort)
                        .id("header-$index").addTo(this)
                }
                is RosterEpoxyItem.PlayerItem -> {
                    RosterPlayerItemEpoxyModel(
                        imageUrl = dataItem.player.headshot,
                        name = dataItem.player.displayName,
                        number = dataItem.player.jersey,
                        position = dataItem.player.position,
                        age = dataItem.player.age,
                        height = dataItem.player.displayHeight,
                        backgroundColor = if (index % 2 == 0) {
                            ContextCompat.getColor(SimpleNFLApplication.context, R.color.dark_grey)
                        } else {
                            ContextCompat.getColor(SimpleNFLApplication.context, R.color.darkish_grey)
                        }

                    ).id(index).addTo(this)
                    SpacerRosterItemEpoxyItem().id("spacer-${dataItem.player.id}").addTo(this)
                }
                is RosterEpoxyItem.FooterItem -> {
                    // do nothing
//                    SectionBottomEpoxyModel(useSection = true).id("footer-$index").addTo(this)
                }
            }
        }

    }
}