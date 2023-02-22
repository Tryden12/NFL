package com.tryden.simplenfl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.models.article.ArticleResponse
import com.tryden.simplenfl.network.response.models.news.NewsResponse
import com.tryden.simplenfl.network.response.models.player.PlayerResponse
import com.tryden.simplenfl.network.response.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.models.team.TeamObjectResponse
import com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse
import com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

   private val repository = SharedRepository()

    private val _teamByIdLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.team.TeamObjectResponse?>()
    val teamByIdLiveData: LiveData<com.tryden.simplenfl.network.response.models.team.TeamObjectResponse?> = _teamByIdLiveData

    private val _allTeamsListLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse?>()
    val allTeamsListLiveData: LiveData<com.tryden.simplenfl.network.response.models.teams.AllTeamsResponse?> = _allTeamsListLiveData

    private val _rosterByTeamIdLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.roster.RosterResponse?>()
    val rosterByTeamId: LiveData<com.tryden.simplenfl.network.response.models.roster.RosterResponse?> = _rosterByTeamIdLiveData

    private val _scoreboardByRangeLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse?>()
    val scoreboardByRangeLiveData: LiveData<com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse?> = _scoreboardByRangeLiveData

    private val _scoresByWeekLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse?>()
    val scoresByWeekLiveData: LiveData<com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse?> = _scoresByWeekLiveData

    private val _scoresCalendarLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse?>()
    val scoresCalendarLiveData: LiveData<com.tryden.simplenfl.network.response.models.scores.ScoreboardResponse?> = _scoresCalendarLiveData

    private val _newsBreakingLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.news.NewsResponse?>()
    val newsBreakingLiveData: LiveData<com.tryden.simplenfl.network.response.models.news.NewsResponse?> = _newsBreakingLiveData

    private val _newsByTeamIdLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.news.NewsResponse?>()
    val newsByTeamIdLiveData: LiveData<com.tryden.simplenfl.network.response.models.news.NewsResponse?> = _newsByTeamIdLiveData

    private val _articleByIdLiveDataResponse = MutableLiveData<com.tryden.simplenfl.network.response.models.article.ArticleResponse?>()
    val articleByIdLiveDataResponse: LiveData<com.tryden.simplenfl.network.response.models.article.ArticleResponse?> = _articleByIdLiveDataResponse

    private val _playerByIdLiveData = MutableLiveData<com.tryden.simplenfl.network.response.models.player.PlayerResponse?>()
    val playerByIdLiveData: LiveData<com.tryden.simplenfl.network.response.models.player.PlayerResponse?> = _playerByIdLiveData

    private val _onTeamSelectedLiveData = MutableLiveData("1")
    val onTeamSelectedLiveData: LiveData<String> = _onTeamSelectedLiveData

    private val _onArticleSelectedLiveData = MutableLiveData<String>()
    val onArticleSelectedLiveData: LiveData<String> = _onArticleSelectedLiveData

    fun saveCurrentTeamId(teamId: String) {
        _onTeamSelectedLiveData.value = teamId
    }

    fun saveCurrentArticleId(articleId: String) {
        _onArticleSelectedLiveData.value = articleId
    }




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

    fun refreshScores(dates: String, limit: String) {
        viewModelScope.launch {
            val response = repository.getScoresRange(dates, limit)

            _scoreboardByRangeLiveData.postValue(response)
        }
    }

    fun refreshScoresCalendar(limit: String) {
        viewModelScope.launch {
            val response = repository.getScoresCalendar(limit)

            _scoresCalendarLiveData.postValue(response)
        }
    }

    fun refreshScoresByWeek(week: String) {
        viewModelScope.launch {
            val response = repository.getScoresByWeek(week)

            _scoresByWeekLiveData.postValue(response)
        }
    }

    fun refreshBreakingNews(type: String, limit: String) {
        viewModelScope.launch {
            val response = repository.getBreakingNews(type, limit)

            _newsBreakingLiveData.postValue(response)
        }
    }

    fun refreshNewsByTeamId(teamId: String, limit: String) {
        viewModelScope.launch {
            val response = repository.getNewsByTeamId(teamId, limit)

            _newsByTeamIdLiveData.postValue(response)
        }
    }

    fun refreshArticle(articleId: String) {
        viewModelScope.launch {
            val response = repository.getArticleById(articleId)

            _articleByIdLiveDataResponse.postValue(response)
        }
    }

    fun refreshPlayer(playerId: String) {
        viewModelScope.launch {
            val response = repository.getPlayerById(playerId)

            _playerByIdLiveData.postValue(response)
        }
    }
}