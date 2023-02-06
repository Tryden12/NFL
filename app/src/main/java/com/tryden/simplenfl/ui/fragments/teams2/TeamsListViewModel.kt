package com.tryden.simplenfl.ui.fragments.teams2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.teams.Teams
import kotlinx.coroutines.launch

class TeamsListViewModel: ViewModel() {

    private val repository = TeamsRepository()
    val uiTeamMapper = UiTeamMapper()

    private val _teamsList = MutableLiveData<List<Teams>>(emptyList())
    val teamListLiveData: LiveData<List<Teams>> = _teamsList

    fun refreshTeams() = viewModelScope.launch {
        val teamsResponse = repository.getAllTeams() ?: return@launch // todo error handling
        _teamsList.postValue(teamsResponse.sports[0].leagues[0].teams)
    }
}