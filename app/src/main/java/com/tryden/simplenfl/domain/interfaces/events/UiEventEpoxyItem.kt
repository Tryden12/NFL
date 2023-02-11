package com.tryden.simplenfl.domain.interfaces.events


interface UiEventEpoxyItem {

    data class HeaderItem(val date: String): UiEventEpoxyItem
    data class EventItem(val event: UiEvent): UiEventEpoxyItem
    object FooterItem: UiEventEpoxyItem
    object Spacer: UiEventEpoxyItem

}