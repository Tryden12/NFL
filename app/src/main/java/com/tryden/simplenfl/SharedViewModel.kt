package com.tryden.simplenfl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.article.Article
import com.tryden.simplenfl.network.response.teams.models.news.News
import com.tryden.simplenfl.network.response.teams.models.player.Player
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

    private val _playerByIdLiveData = MutableLiveData<Player?>()
    val playerByIdLiveData: LiveData<Player?> = _playerByIdLiveData

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

    fun refreshScoreboard(dates: String, limit: String) {
        viewModelScope.launch {
            val response = repository.getScoreboardRange(dates, limit)

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

    fun refreshPlayer(playerId: String) {
        viewModelScope.launch {
            val response = repository.getPlayerById(playerId)

            _playerByIdLiveData.postValue(response)
        }
    }
}