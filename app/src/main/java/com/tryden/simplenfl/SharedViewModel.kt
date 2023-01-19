package com.tryden.simplenfl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.article.Article
import com.tryden.simplenfl.network.response.teams.models.news.News
import com.tryden.simplenfl.network.response.teams.models.roster.Roster
import com.tryden.simplenfl.network.response.teams.models.scoreboard.Scoreboard
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject
import com.tryden.simplenfl.network.response.teams.models.teams.Sports
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

   private val repository = SharedRepository()

    private val _teamByIdLiveData = MutableLiveData<TeamObject?>()
    val teamByIdLiveData: LiveData<TeamObject?> = _teamByIdLiveData

    private val _allTeamsListLiveData = MutableLiveData<Sports?>()
    val allTeamsListLiveData: LiveData<Sports?> = _allTeamsListLiveData

    private val _rosterByTeamIdLiveData = MutableLiveData<Roster?>()
    val rosterByTeamId: LiveData<Roster?> = _rosterByTeamIdLiveData

    private val _scoreboardByRangeLiveData = MutableLiveData<Scoreboard?>()
    val scoreboardByRangeLiveData: LiveData<Scoreboard?> = _scoreboardByRangeLiveData

    private val _newsBreakingLiveData = MutableLiveData<News?>()
    val newsBreaking: LiveData<News?> = _newsBreakingLiveData

    private val _articleByIdLiveData = MutableLiveData<Article?>()
    val articleById: LiveData<Article?> = _articleByIdLiveData

    fun refreshTeam(teamId: Int) {
        viewModelScope.launch {
            val response = repository.getTeamById(teamId)

            _teamByIdLiveData.postValue(response)
        }
    }

    fun refreshTeamsList() {
        viewModelScope.launch {
            val response = repository.getAllTeams()

            _allTeamsListLiveData.postValue(response)
        }
    }

    fun refreshRoster(teamId: Int) {
        viewModelScope.launch {
            val response = repository.getRosterByTeamId(teamId)

            _rosterByTeamIdLiveData.postValue(response)
        }
    }

    fun refreshScoreboard() {
        viewModelScope.launch {
            val response = repository.getScoreboardRange()

            _scoreboardByRangeLiveData.postValue(response)
        }
    }

    fun refreshBreakingNews() {
        viewModelScope.launch {
            val response = repository.getBreakingNews()

            _newsBreakingLiveData.postValue(response)
        }
    }

    fun refreshArticle(articleId: String) {
        viewModelScope.launch {
            val response = repository.getArticleById(articleId)

            _articleByIdLiveData.postValue(response)
        }
    }
}