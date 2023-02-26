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

class TeamRosterEpoxyController: TypedEpoxyController<List<RosterEpoxyItem>>() {

    override fun buildModels(items: List<RosterEpoxyItem>) {
        if (items.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        items.forEachIndexed { index, item ->

            when (item) {
                is RosterEpoxyItem.HeaderItem -> {
                    RosterHeaderEpoxyModel(rosterType = item.header)
                        .id("header-$index").addTo(this)
                }
                is RosterEpoxyItem.PlayerItem -> {
                    RosterPlayerItemEpoxyModel(
                        imageUrl = item.player.headshot,
                        name = item.player.displayName,
                        number = item.player.jersey,
                        position = item.player.position,
                        age = item.player.age,
                        height = item.player.displayHeight,
                        backgroundColor = ContextCompat.getColor(SimpleNFLApplication.context, R.color.dark_grey) // TODO
                    ).id(index).addTo(this)
                    SpacerRosterItemEpoxyItem().id("spacer-${item.player.id}").addTo(this)

                }
                is RosterEpoxyItem.FooterItem -> {
                    // do nothing
//                    SectionBottomEpoxyModel(useSection = true).id("footer-$index").addTo(this)
                }
            }
        }
    }
}