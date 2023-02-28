package com.tryden.simplenfl.ui.models

import com.tryden.simplenfl.ui.epoxy.interfaces.team.RosterEpoxyItem

data class RosterViewState(
    val dataList: List<RosterEpoxyItem> = emptyList(),
    val isLoading: Boolean = true,
    val sort: Sort = Sort.NAME
) {

    enum class Sort {
        NAME, POSITION, AGE, HEIGHT
    }
}