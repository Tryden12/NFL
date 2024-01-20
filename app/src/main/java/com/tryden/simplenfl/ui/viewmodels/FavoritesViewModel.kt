package com.tryden.simplenfl.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.data.local.AppDatabase
import com.tryden.simplenfl.data.local.dao.FavoriteTeamDao
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity
import com.tryden.simplenfl.domain.models.news.FavoriteHeadline
import com.tryden.simplenfl.domain.usecase.favoriteTeams.FavoriteTeamsUseCase
import com.tryden.simplenfl.domain.usecase.news.byTeamID.NewsByTeamIdUseCase
import com.tryden.simplenfl.ui.formatPublishedTime
import com.tryden.simplenfl.ui.epoxy.interfaces.news.FavoritesHeadlinesEpoxyItem
import com.tryden.simplenfl.util.Constants.MY_NEWS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteTeamsUseCase: FavoriteTeamsUseCase,
    private val newsByTeamIdUseCase: NewsByTeamIdUseCase
) : ViewModel() {

    init {
        getAllFavoriteTeams()
    }

    // region LiveData variables
    private val _allFavoriteTeams = MutableLiveData<List<FavoriteTeamEntity>>(emptyList())
    val allFavoriteTeams: LiveData<List<FavoriteTeamEntity>> = _allFavoriteTeams

    private val _newsFromFavorites = MutableLiveData<List<FavoritesHeadlinesEpoxyItem>>()
    val newsFromFavorites: LiveData<List<FavoritesHeadlinesEpoxyItem>> = _newsFromFavorites
    // endregion LiveData variables


    // region FavoriteTeamEntity
    fun getAllFavoriteTeams() {
        viewModelScope.launch {
            favoriteTeamsUseCase.getAllFavoriteTeams().collect { result ->
                if (result.isNotEmpty()) {
                    _allFavoriteTeams.postValue(result)
                } else {
                    _allFavoriteTeams.postValue(result)
                    Log.d("FavoritesViewModel()", "Favorite teams list size = 0")
                }
            }
        }
    }

    fun deleteFavoriteTeam(entity: FavoriteTeamEntity) = viewModelScope.launch {
        favoriteTeamsUseCase.deleteFavoriteTeam(entity)
    }

    fun updateFavoriteTeam(entity: FavoriteTeamEntity) = viewModelScope.launch {
        favoriteTeamsUseCase.updateFavoriteTeam(entity)
    }

    fun addFavoriteTeam(entity: FavoriteTeamEntity) = viewModelScope.launch {
        favoriteTeamsUseCase.insertFavoriteTeam(entity)
    }

    // endregion FavoriteTeamEntity


    //TODO: Fix the section below
    // region HomeFragment news from favorites

    fun getNewsByTeamId(favoriteTeams: List<FavoriteTeamEntity>, limit: String) {
        val favoriteTeamsNews = mutableListOf<FavoriteHeadline>()

        viewModelScope.launch {
            favoriteTeams.forEach { team ->
                Log.d("FavoritesViewModel()", "favoriteTeam id: ${team.shortName}")
                val news = newsByTeamIdUseCase.getNewsByTeamId(team.id, limit)
                news?.forEach { headline ->
                    favoriteTeamsNews.add(
                        FavoriteHeadline(
                            articleId = headline.articleId,
                            headline = headline.title,
                            shortDescription = headline.shortDescription,
                            articleImage = headline.articleImage,
                            teamLogo = team.logo,
                            teamName = team.shortName,
                            teamColor = team.color,
                            timeSincePosted = formatPublishedTime(headline.published),
                            author = headline.author
                        )
                    )
                }
            }
            Log.d("FavoritesViewModel()", "favoriteTeamsNews size: ${favoriteTeamsNews.size}")
            // build epoxy items
            val epoxyItems = buildList {
                add(FavoritesHeadlinesEpoxyItem.HeaderItem(headerTitle = MY_NEWS))
                favoriteTeamsNews
                    .sortedByDescending { it.timeSincePosted }
                    .forEach {
                    add(FavoritesHeadlinesEpoxyItem.FavoriteHeadlineItem(it))
                }
                add(FavoritesHeadlinesEpoxyItem.FooterItem)
            }
            _newsFromFavorites.postValue(epoxyItems)
        }
    }

//    fun refreshHeadlinesByTeamId(favoriteTeams: List<FavoriteTeamEntity>, limit: String) {
//        val news = mutableListOf<FavoriteHeadline>()
//
//        viewModelScope.launch {
//            favoriteTeams.forEach { team ->
//                val headlines = repository.getHeadlinesByTeamId(team.id, limit)
//               headlines?.forEach { headline ->
//                   news.add(FavoriteHeadline(
//                       articleId = headline.articleId,
//                       headline = headline.title,
//                       shortDescription = headline.shortDescription,
//                       articleImage = headline.articleImage,
//                       teamLogo = team.logo,
//                       teamName = team.shortName,
//                       teamColor = team.color,
//                       timeSincePosted = formatPublishedTime(headline.published),
//                       author = headline.author
//                   ))
//               }
//
//            }
//
//            // build epoxy items
//            val epoxyItems = buildList {
//                add(FavoritesHeadlinesEpoxyItem.HeaderItem(headerTitle = "My News"))
//                news.forEach {
//                    add(FavoritesHeadlinesEpoxyItem.FavoriteHeadlineItem(it))
//                }
//                add(FavoritesHeadlinesEpoxyItem.FooterItem)
//            }
//            _newsFromFavorites.postValue(epoxyItems)
//        }
//    }
    // endregion HomeFragment news from favorites

}