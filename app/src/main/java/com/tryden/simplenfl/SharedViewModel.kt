package com.tryden.simplenfl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.scoreboard.Scoreboard
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

   private val repository = SharedRepository()

    private val _teamByIdLiveData = MutableLiveData<TeamObject?>()
    val teamByIdLiveData: LiveData<TeamObject?> = _teamByIdLiveData

    private val _scoreboardByRangeLiveData = MutableLiveData<Scoreboard?>()
    val scoreboardByRangeLiveData: LiveData<Scoreboard?> = _scoreboardByRangeLiveData

    fun refreshTeam(teamId: Int) {
        viewModelScope.launch {
            val response = repository.getTeamById(teamId)

            _teamByIdLiveData.postValue(response)
        }
    }

    fun refreshScoreboard() {
        viewModelScope.launch {
            val response = repository.getScoreboardRange()

            _scoreboardByRangeLiveData.postValue(response)
        }
    }
}