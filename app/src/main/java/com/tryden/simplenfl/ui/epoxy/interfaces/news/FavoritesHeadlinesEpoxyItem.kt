package com.tryden.simplenfl.ui.epoxy.interfaces.news

import com.tryden.simplenfl.domain.models.news.FavoriteHeadline

interface FavoritesHeadlinesEpoxyItem {

    data class HeaderItem(val headerTitle: String): FavoritesHeadlinesEpoxyItem
    data class FavoriteHeadlineItem(val newsItem: FavoriteHeadline): FavoritesHeadlinesEpoxyItem
    object FooterItem: FavoritesHeadlinesEpoxyItem
    object Spacer: FavoritesHeadlinesEpoxyItem

}