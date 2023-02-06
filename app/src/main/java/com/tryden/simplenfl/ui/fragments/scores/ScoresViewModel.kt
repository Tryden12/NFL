package com.tryden.simplenfl.ui.fragments.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.scores.Event
import kotlinx.coroutines.launch

class ScoresViewModel : ViewModel(){

    private val repository = ScoresRepository()
    val uiEventMapper = UiEventMapper()
    val uiCompetitorMapper = UiCompetitorMapper()

    // List of events
    private val _eventList = MutableLiveData<List<Event>>(emptyList())
    val eventListLiveData: LiveData<List<Event>> = _eventList

    fun refreshScores(date: String, limit: String) = viewModelScope.launch {
        val eventsResponse = repository.refreshScores(date, limit) ?: return@launch // todo error handeling
        _eventList.postValue(eventsResponse.events)
    }


}