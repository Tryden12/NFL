package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.scores.Calendar
import com.tryden.simplenfl.network.response.teams.models.scores.Event
import com.tryden.simplenfl.domain.models.calendar.UiCalendarMapper
import com.tryden.simplenfl.domain.models.calendar.UiWeekMapper
import com.tryden.simplenfl.ui.repositories.ScoresRepository
import com.tryden.simplenfl.domain.mappers.events.UiEventMapper
import kotlinx.coroutines.launch

class ScoresViewModel : ViewModel(){

    private val repository = ScoresRepository()
    val uiEventMapper = UiEventMapper
    val uiCalendarMapper = UiCalendarMapper()
    val uiWeeksMapper = UiWeekMapper()

    // List of events
    private val _eventList = MutableLiveData<List<Event>>(emptyList())
    val eventListLiveData: LiveData<List<Event>> = _eventList

    // List of weeks (calendar)
    private val _calendarList = MutableLiveData<List<Calendar>>(emptyList())
    val calendarListLiveData: LiveData<List<Calendar>> = _calendarList

    fun refreshScores(date: String, limit: String) = viewModelScope.launch {
        val eventsResponse = repository.getScores(date, limit) ?: return@launch // todo error handling
        _eventList.postValue(eventsResponse.events)
    }

    fun refreshCalendar(limit: String) = viewModelScope.launch {
        val calendarResponse = repository.getScoresCalendar(limit) ?: return@launch // todo error handling
        _calendarList.postValue(calendarResponse.leagues[0].calendar)
    }


}