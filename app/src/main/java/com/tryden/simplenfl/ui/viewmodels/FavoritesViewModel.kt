package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.database.AppDatabase
import com.tryden.simplenfl.database.entity.FavoriteTeamEntity
import com.tryden.simplenfl.ui.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoritesViewModel() : ViewModel() {

    val allFavoriteTeamsFlow: Flow<List<FavoriteTeamEntity>>
    private val repository: FavoritesRepository

    init {
        val dao = AppDatabase.getDatabase(SimpleNFLApplication.context).favoriteTeamDao()
        repository = FavoritesRepository(dao)
        allFavoriteTeamsFlow = repository.getAllFavoriteTeams()

    }

    // region FavoriteTeamEntity
    fun deleteFavoriteTeam(favoriteTeamEntity: FavoriteTeamEntity) = viewModelScope.launch {
        repository.delete(favoriteTeamEntity)
    }

    fun updateFavoriteTeam(favoriteTeamEntity: FavoriteTeamEntity) = viewModelScope.launch {
        repository.update(favoriteTeamEntity)
    }

    fun addFavoriteTeam(favoriteTeamEntity: FavoriteTeamEntity) = viewModelScope.launch {
        repository.insert(favoriteTeamEntity)
    }

    // endregion FavoriteTeamEntity


}