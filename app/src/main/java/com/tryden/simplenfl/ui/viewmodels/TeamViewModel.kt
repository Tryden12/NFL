package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.domain.mappers.team.TeamMapper
import com.tryden.simplenfl.domain.mappers.team.TeamNewsMapper
import com.tryden.simplenfl.network.response.models.news.Article
import com.tryden.simplenfl.network.response.models.team.TeamResponse
import com.tryden.simplenfl.ui.repositories.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val repository = TeamRepository()
    val teamMapper = TeamMapper
    val teamNewsMapper = TeamNewsMapper


    private val _teamById = MutableLiveData<TeamResponse.Team?>()
    val teamByIdLiveData: LiveData<TeamResponse.Team?> = _teamById

    private val _newsByTeamId = MutableLiveData<List<Article?>>()
    val newsByTeamIdLiveData: LiveData<List<Article?>> = _newsByTeamId

    fun refreshTeam(teamId: String) {
        viewModelScope.launch {
            val response = repository.getTeamById(teamId)

            _teamById.postValue(response?.team)
        }
    }

    fun refreshNewsByTeamId(teamId: String, limit: String) {
        viewModelScope.launch {
            val response = repository.getNewsByTeamId(teamId, limit)

            _newsByTeamId.postValue(response!!.articles)
        }
    }
}