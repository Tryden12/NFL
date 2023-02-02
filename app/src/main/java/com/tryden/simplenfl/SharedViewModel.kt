package com.tryden.simplenfl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.network.response.teams.models.article.ArticleResponse
import com.tryden.simplenfl.network.response.teams.models.news.NewsResponse
import com.tryden.simplenfl.network.response.teams.models.player.PlayerResponse
import com.tryden.simplenfl.network.response.teams.models.roster.RosterResponse
import com.tryden.simplenfl.network.response.teams.models.team.TeamObjectResponse
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeamsResponse
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

   private val repository = SharedRepository()

    private val _teamByIdLiveData = MutableLiveData<TeamObjectResponse?>()
    val teamByIdLiveData: LiveData<TeamObjectResponse?> = _teamByIdLiveData

    private val _allTeamsListLiveData = MutableLiveData<AllTeamsResponse?>()
    val allTeamsListLiveData: LiveData<AllTeamsResponse?> = _allTeamsListLiveData

    private val _rosterByTeamIdLiveData = MutableLiveData<RosterResponse?>()
    val rosterByTeamId: LiveData<RosterResponse?> = _rosterByTeamIdLiveData

    private val _scoreboardByRangeLiveData = MutableLiveData<ScoreboardResponse?>()
    val scoreboardByRangeLiveData: LiveData<ScoreboardResponse?> = _scoreboardByRangeLiveData

    private val _scoresByWeekLiveData = MutableLiveData<ScoreboardResponse?>()
    val scoresByWeekLiveData: LiveData<ScoreboardResponse?> = _scoresByWeekLiveData

    private val _scoresCalendarLiveData = MutableLiveData<ScoreboardResponse?>()
    val scoresCalendarLiveData: LiveData<ScoreboardResponse?> = _scoresCalendarLiveData

    private val _newsBreakingLiveData = MutableLiveData<NewsResponse?>()
    val newsBreakingLiveData: LiveData<NewsResponse?> = _newsBreakingLiveData

    private val _newsByTeamIdLiveData = MutableLiveData<NewsResponse?>()
    val newsByTeamIdLiveData: LiveData<NewsResponse?> = _newsByTeamIdLiveData

    private val _articleByIdLiveDataResponse = MutableLiveData<ArticleResponse?>()
    val articleByIdLiveDataResponse: LiveData<ArticleResponse?> = _articleByIdLiveDataResponse

    private val _playerByIdLiveData = MutableLiveData<PlayerResponse?>()
    val playerByIdLiveData: LiveData<PlayerResponse?> = _playerByIdLiveData

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

    fun refreshScoreboard(dates: String, limit: String) {
        viewModelScope.launch {
            val response = repository.getScoreboardRange(dates, limit)

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