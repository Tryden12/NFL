package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.models.scores.Calendar
import com.tryden.simplenfl.network.response.models.scores.Event
import com.tryden.simplenfl.domain.models.calendar.UiCalendarMapper
import com.tryden.simplenfl.ui.repositories.ScoresRepository
import com.tryden.simplenfl.domain.mappers.events.EventMapper
import kotlinx.coroutines.launch

class ScoresViewModel : ViewModel(){

    private val repository = ScoresRepository()
    val uiCalendarMapper = UiCalendarMapper()
    val uiEventMapper = EventMapper

    // List of events
    private val _eventList = MutableLiveData<List<com.tryden.simplenfl.network.response.models.scores.Event>>(emptyList())
    val eventListLiveData: LiveData<List<com.tryden.simplenfl.network.response.models.scores.Event>> = _eventList

    // List of weeks (calendar)
    private val _calendarList = MutableLiveData<List<com.tryden.simplenfl.network.response.models.scores.Calendar>>(emptyList())
    val calendarListLiveData: LiveData<List<com.tryden.simplenfl.network.response.models.scores.Calendar>> = _calendarList

    fun refreshScores(date: String, limit: String) = viewModelScope.launch {
        val eventsResponse = repository.getScores(date, limit) ?: return@launch // todo error handling
        _eventList.postValue(eventsResponse.events)
    }

    fun refreshCalendar(limit: String) = viewModelScope.launch {
        val calendarResponse = repository.getScoresCalendar(limit) ?: return@launch // todo error handling
        _calendarList.postValue(calendarResponse.leagues[0].calendar)
    }


}