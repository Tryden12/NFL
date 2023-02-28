package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.domain.mappers.team.TeamMapper
import com.tryden.simplenfl.domain.mappers.team.TeamNewsMapper
import com.tryden.simplenfl.domain.mappers.team.TeamRosterMapper
import com.tryden.simplenfl.domain.models.roster.Player
import com.tryden.simplenfl.domain.models.team.Logo
import com.tryden.simplenfl.domain.models.team.Team
import com.tryden.simplenfl.network.response.models.news.Article
import com.tryden.simplenfl.network.response.models.team.TeamResponse
import com.tryden.simplenfl.ui.epoxy.interfaces.news.HeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.interfaces.team.RosterEpoxyItem
import com.tryden.simplenfl.ui.models.RosterViewState
import com.tryden.simplenfl.ui.repositories.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val repository = TeamRepository()

    // Team Header
    private val _teamHeader = MutableLiveData<Team?>()
    val teamHeaderLiveData: LiveData<Team?> = _teamHeader

    // Team logo
    private val _teamLogo = MutableLiveData<Logo?>()
    val teamLogoLiveData: LiveData<Logo?> = _teamLogo

    // Headlines by team id
    private val _headlines = MutableLiveData<List<HeadlinesEpoxyItem>>()
    val headlinesLiveData: LiveData<List<HeadlinesEpoxyItem>> = _headlines

    // Roster page
    var currentSort: RosterViewState.Sort = RosterViewState.Sort.NAME
        set(value) {
            field = value
            updateRosterViewState(rosterMapByTeamIdLiveData.value)
        }

    private val _rosterMapByTeamId = MutableLiveData<Map<String, List<Player>>>()
    val rosterMapByTeamIdLiveData: LiveData<Map<String, List<Player>>> = _rosterMapByTeamId

    private val _rosterViewState = MutableLiveData<RosterViewState>()
    val rosterViewStateLiveData: LiveData<RosterViewState>
        get() = _rosterViewState


    fun refreshTeamHeader(teamId: String) {
        viewModelScope.launch {
            val team = repository.getTeamHeader(teamId)

            _teamHeader.postValue(team)
        }
    }

    fun refreshTeamLogo(teamId: String) {
        viewModelScope.launch {
            val logo = repository.getTeamLogo(teamId)

            _teamLogo.postValue(logo)
        }
    }

    fun refreshHeadlinesByTeamId(teamId: String, limit: String) {
        viewModelScope.launch {
            val headlines = repository.getHeadlinesByTeamId(teamId, limit)

            // create epoxy items list
            val epoxyItems = buildList {
                add(HeadlinesEpoxyItem.HeaderItem(headerTitle = "Top Headlines"))
                headlines?.forEach {
                    add(HeadlinesEpoxyItem.HeadlineItem(headline = it))
                }
                add(HeadlinesEpoxyItem.FooterItem)
            }

            _headlines.postValue(epoxyItems)
        }
    }

    // Refresh roster response, and post domain model
    fun refreshRoster(teamId: String) {
        viewModelScope.launch {
            val rosterMap = repository.getRosterByTeamId(teamId)

            _rosterMapByTeamId.postValue(rosterMap!!)
        }
    }

    // Post & update roster view state
    private fun updateRosterViewState(dataMap: Map<String, List<Player>>?){
        var dataListSorted: List<RosterEpoxyItem> = emptyList()
        when (currentSort) {
            RosterViewState.Sort.NAME -> {
                 dataListSorted = buildList {
                     dataMap!!.forEach {
                         add(RosterEpoxyItem.HeaderItem(header = it.key))
                         it.value.sortedBy { it.firstName }.forEach { player ->
                             add(RosterEpoxyItem.PlayerItem(player = player))
                         }
                         add(RosterEpoxyItem.FooterItem)
                     }
                 }
            }
            RosterViewState.Sort.POSITION -> {
                dataListSorted = buildList {
                    dataMap!!.forEach {
                        add(RosterEpoxyItem.HeaderItem(header = it.key))
                        it.value.sortedBy { it.position }.forEach { player ->
                            add(RosterEpoxyItem.PlayerItem(player = player))
                        }
                        add(RosterEpoxyItem.FooterItem)
                    }
                }
            }
            RosterViewState.Sort.AGE -> {
                dataListSorted = buildList {
                    dataMap!!.forEach {
                        add(RosterEpoxyItem.HeaderItem(header = it.key))
                        it.value.sortedBy { it.age }.forEach { player ->
                            add(RosterEpoxyItem.PlayerItem(player = player))
                        }
                        add(RosterEpoxyItem.FooterItem)
                    }
                }
            }
            RosterViewState.Sort.HEIGHT -> {
                dataListSorted = buildList {
                    dataMap!!.forEach {
                        add(RosterEpoxyItem.HeaderItem(header = it.key))
                        it.value.sortedBy { it.displayHeight }.forEach { player ->
                            add(RosterEpoxyItem.PlayerItem(player = player))
                        }
                        add(RosterEpoxyItem.FooterItem)
                    }
                }
            }
        }

        _rosterViewState.postValue(
            RosterViewState(
                dataList = dataListSorted,
                isLoading = false,
                sort = currentSort
            )
        )
    }
}