package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.domain.models.calendar.UiCalendar
import com.tryden.simplenfl.domain.usecase.scores.byRange.ScoresRangeUseCase
import com.tryden.simplenfl.domain.usecase.scores.calendar.ScoresCalendarUseCase
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val scoresRangeUseCase: ScoresRangeUseCase,
    private val scoresCalendarUseCase: ScoresCalendarUseCase
): ViewModel(){

    // List of events
    private val _eventList = MutableLiveData<List<EventEntity>>(emptyList())
    val eventListLiveData: LiveData<List<EventEntity>> = _eventList

    // List of weeks (calendar)
    private val _calendarList = MutableLiveData<List<UiCalendar>>(emptyList())
    val calendarListLiveData: LiveData<List<UiCalendar>> = _calendarList

    fun refreshScores(date: String, limit: String) = viewModelScope.launch {
        val events = scoresRangeUseCase.getScoresRange(date, limit)
        _eventList.postValue(events)
    }

    fun refreshCalendar(limit: String) = viewModelScope.launch {
        val calendar = scoresCalendarUseCase.getScoresCalendar(limit)
        _calendarList.postValue(calendar)
    }

}