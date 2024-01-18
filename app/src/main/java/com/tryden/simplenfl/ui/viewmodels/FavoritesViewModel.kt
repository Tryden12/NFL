package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.data.local.AppDatabase
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.domain.models.news.FavoriteHeadline
import com.tryden.simplenfl.ui.formatPublishedTime
import com.tryden.simplenfl.ui.epoxy.interfaces.news.FavoritesHeadlinesEpoxyItem
import com.tryden.simplenfl.ui.repositories.FavoritesRepository
import com.tryden.simplenfl.ui.repositories.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoritesViewModel() : ViewModel() {

    val allFavoriteTeamsFlow: Flow<List<FavoriteTeamEntity>>
    private val repository: FavoritesRepository
    private val teamRepository: TeamRepository

    init {
        val dao = AppDatabase.getDatabase(SimpleNFLApplication.context).favoriteTeamDao()
        repository = FavoritesRepository(dao)
        allFavoriteTeamsFlow = repository.getAllFavoriteTeams()

        teamRepository = TeamRepository()
    }

    // region LiveData and Flow variables
    private val _newsFromFavorites = MutableLiveData<List<FavoritesHeadlinesEpoxyItem>>()
    val newsFromFavorites: LiveData<List<FavoritesHeadlinesEpoxyItem>> = _newsFromFavorites

    // endregion LiveData and Flow variables

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


    // region HomeFragment news from favorites
    fun refreshHeadlinesByTeamId(favoriteTeams: List<FavoriteTeamEntity>, limit: String) {
        val news = mutableListOf<FavoriteHeadline>()

        viewModelScope.launch {
            favoriteTeams.forEach { team ->
                val headlines = repository.getHeadlinesByTeamId(team.id, limit)
               headlines?.forEach { headline ->
                   news.add(FavoriteHeadline(
                       articleId = headline.articleId,
                       headline = headline.title,
                       shortDescription = headline.shortDescription,
                       articleImage = headline.articleImage,
                       teamLogo = team.logo,
                       teamName = team.shortName,
                       teamColor = team.color,
                       timeSincePosted = formatPublishedTime(headline.published),
                       author = headline.author
                   ))
               }

            }

            // build epoxy items
            val epoxyItems = buildList {
                add(FavoritesHeadlinesEpoxyItem.HeaderItem(headerTitle = "My News"))
                news.forEach {
                    add(FavoritesHeadlinesEpoxyItem.FavoriteHeadlineItem(it))
                }
                add(FavoritesHeadlinesEpoxyItem.FooterItem)
            }
            _newsFromFavorites.postValue(epoxyItems)
        }
    }
    // endregion HomeFragment news from favorites

}