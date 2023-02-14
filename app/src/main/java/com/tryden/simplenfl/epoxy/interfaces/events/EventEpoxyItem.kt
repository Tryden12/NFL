package com.tryden.simplenfl.epoxy.interfaces.events


interface EventEpoxyItem {

    data class HeaderItem(val header: String): EventEpoxyItem
    data class EventItem(val event: EventEntity): EventEpoxyItem
    object FooterItem: EventEpoxyItem
    object Spacer: EventEpoxyItem

}