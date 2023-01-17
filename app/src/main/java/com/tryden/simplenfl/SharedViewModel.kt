package com.tryden.simplenfl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

   private val repository = SharedRepository()

    private val _teamByIdLiveData = MutableLiveData<com.tryden.simplenfl.network.response.teams.models.team.TeamObject?>()
    val teamByIdLiveData: LiveData<com.tryden.simplenfl.network.response.teams.models.team.TeamObject?> = _teamByIdLiveData

    fun refreshTeam(teamId: Int) {
        viewModelScope.launch {
            val response = repository.getTeamById(teamId)

            _teamByIdLiveData.postValue(response)
        }
    }
}