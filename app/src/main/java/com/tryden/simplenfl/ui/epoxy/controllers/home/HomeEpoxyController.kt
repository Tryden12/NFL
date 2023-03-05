package com.tryden.simplenfl.ui.epoxy.controllers.home

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.addLoadingModel
import com.tryden.simplenfl.ui.epoxy.interfaces.news.FavoritesHeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.interfaces.news.HeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.SectionFooterEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.news.HeadlineItemEpoxyModel

class HomeEpoxyController(
    private val onArticleSelected: (String) -> Unit
): EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var headlineEpoxyItems: List<HeadlinesEpoxyItem> = emptyList()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    var favoriteHeadlinesEpoxyItems: List<FavoritesHeadlinesEpoxyItem> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            addLoadingModel()
            return
        }

        // Add headline items
        headlineEpoxyItems.forEach { item ->
            when (item) {
                is HeadlinesEpoxyItem.HeaderItem -> {
                    SectionHeaderEpoxyModel(
                        title = item.headerTitle,
                        logo = "",
                        logoVisible = true
                    ).id("header-headlines").addTo(this)
                }
                is HeadlinesEpoxyItem.HeadlineItem -> {
                    HeadlineItemEpoxyModel(
                        headlineTitle = item.headline.title,
                        articleId = item.headline.articleId,
                        onArticleSelected = onArticleSelected
                    ).id(item.headline.articleId).addTo(this)
                }
                is HeadlinesEpoxyItem.FooterItem -> {
                    SectionFooterEpoxyModel().id("footer-headlines").addTo(this)
                }
                is HeadlinesEpoxyItem.Spacer -> {
                    // do nothing
                }
            }
        }

        if (favoriteHeadlinesEpoxyItems.isNotEmpty()) {
            favoriteHeadlinesEpoxyItems.forEach { item ->
                when (item) {
                    is FavoritesHeadlinesEpoxyItem.HeaderItem -> {
                        SectionHeaderEpoxyModel(
                            title = item.headerTitle,
                            logo = "",
                            logoVisible = false
                        ).id("header-my-news-headlines").addTo(this)
                    }
                    is FavoritesHeadlinesEpoxyItem.FavoriteHeadlineItem -> {
                        // todo
                        Log.e("HomeEpoxyController", "favorite carousel: ${item.newsItem.headline}" )
                    }
                    is FavoritesHeadlinesEpoxyItem.FooterItem -> {
                        SectionFooterEpoxyModel().id("footer-my-news-headlines").addTo(this)
                    }
                    is FavoritesHeadlinesEpoxyItem.Spacer -> {
                        // do nothing
                    }
                }
            }
        }
    }

}