package com.tryden.simplenfl.ui.epoxy.interfaces.team

import com.tryden.simplenfl.domain.models.roster.Player

interface RosterEpoxyItem {
    data class HeaderItem(val header: String): RosterEpoxyItem
    data class PlayerItem(val player: Player): RosterEpoxyItem
    object FooterItem: RosterEpoxyItem
    object Spacer: RosterEpoxyItem
}


