package com.tryden.simplenfl.domain.interfaces.events

sealed interface EventState {

    object Loading: EventState
    data class Error(
        val errorString: String,
        val isConnectedToInternet: Boolean,
        val retry: () -> Unit
    ): EventState

}
