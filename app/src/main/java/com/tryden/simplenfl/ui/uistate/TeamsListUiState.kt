package com.tryden.simplenfl.ui.uistate

import com.tryden.simplenfl.domain.newmodels.TeamList

/**
 * UI state for the teams list screen
 */
data class TeamsListUiState(
    var teams: List<TeamList> = emptyList(),
    val isLoading: Boolean = true,
    val isDataError: Boolean = false,
    val currentTeam: TeamList = TeamList()
)
