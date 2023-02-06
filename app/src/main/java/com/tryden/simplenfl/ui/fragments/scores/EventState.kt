package com.tryden.simplenfl.ui.fragments.scores

sealed interface EventState {

    object Loading: EventState
    data class Error(
        val errorString: String,
        val isConnectedToInternet: Boolean,
        val retry: () -> Unit
    ): EventState

}

sealed interface Event {
    data class Upcoming(
        val homeTeamName: String,
        val awayTeamName: String
        // todo
    ): Event
    data class Completed(
        val homeTeamName: String,
        val awayTeamName: String
        // todo
    ): Event
    data class ProBowl(
        val homeTeamName: String,
        val awayTeamName: String
        // todo
    ): Event
}