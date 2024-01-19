package com.tryden.simplenfl.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.domain.newmodels.TeamList
import com.tryden.simplenfl.domain.usecase.teams.TeamsListUseCase
import com.tryden.simplenfl.ui.uistate.TeamsListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Here we prepare all provided from the data & domain layers for the UI, presentation layer.
 * This class has no knowledge whether data is coming from API or Database,
 * so fulfilling complete abstraction of Data Layer.
 * We are using Flow to keep the data stream unidirectional.
 */
@HiltViewModel
class TeamsListViewModel @Inject constructor(
    private val teamsListUseCase: TeamsListUseCase
) : ViewModel() {

    private val _teamsList = MutableLiveData<List<TeamList>>(emptyList())
    val teamListLiveData: LiveData<List<TeamList>> = _teamsList

    fun refreshTeams() = viewModelScope.launch {
        // CoroutineScope tied to this ViewModel
        // Scope will be cleared when ViewModel will be cleared
        viewModelScope.launch {
            teamsListUseCase.getAllTeams().collect { result ->
                if (result.isNotEmpty()) {
                    _teamsList.postValue(result)
                } else {
                    Log.d("TeamsListViewModel()", "Teams list size = 0")
                }
            }
        }

    }



/** Migrate to below ****************************************************************************/

    private val _uiState = MutableStateFlow(TeamsListUiState())
    val uiState: StateFlow<TeamsListUiState> = _uiState

    init {
        getAllTeams()
    }

    fun getAllTeams() {
        // CoroutineScope tied to this ViewModel
        // Scope will be cleared when ViewModel will be cleared
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            teamsListUseCase.getAllTeams().collect { result ->
                if (result.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            isDataError = false,
                            isLoading = false,
                            teams = result,
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(isDataError = true, isLoading = false, teams = result)
                    }
                }
            }
        }
    }

/****************************************************************************************/

}