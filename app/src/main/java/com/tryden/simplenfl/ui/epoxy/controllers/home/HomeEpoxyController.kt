package com.tryden.simplenfl.ui.epoxy.controllers.home

import android.util.Log
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.ui.addLoadingModel
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.ui.epoxy.interfaces.news.FavoritesHeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.interfaces.news.HeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.SectionFooterEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.news.HeadlineItemEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.news.MyNewsCarouselEpoxyItem

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
            // Get items
            val carouselItems = buildList {
                favoriteHeadlinesEpoxyItems.forEach { item ->
                    when (item) {
                        is FavoritesHeadlinesEpoxyItem.FavoriteHeadlineItem -> {
                            add(MyNewsCarouselEpoxyItem(item.newsItem, onArticleSelected).id("carousel-item-${item.newsItem.articleId}"))
                        }

                    }
                }
            }


            // Build items
            favoriteHeadlinesEpoxyItems.forEachIndexed { index, item ->
                when (item) {
                    is FavoritesHeadlinesEpoxyItem.HeaderItem -> {
                        SectionHeaderEpoxyModel(
                            title = item.headerTitle,
                            logo = "",
                            logoVisible = false
                        ).id("header-my-news-headlines").addTo(this)
                    }
                    is FavoritesHeadlinesEpoxyItem.FavoriteHeadlineItem -> {
                        Log.e("HomeEpoxyController", "favorite carousel: ${item.newsItem.headline}" )
                        Log.e("HomeEpoxyController", "carousel size: ${carouselItems.size}" )

                        if (index == favoriteHeadlinesEpoxyItems.lastIndex-1) {

                            CarouselModel_()
                                .id("my-news-carousel")
                                .onBind { _, view, _ ->
                                    view.apply {
                                        setBackgroundColor(
                                            ContextCompat.getColor(
                                                SimpleNFLApplication.context, R.color.dark_grey
                                            )
                                        )
                                    }
                                }
                                .padding(Carousel.Padding.dp(
                                    12, //left
                                    0, //top
                                    0, //right
                                    0, //bottom
                                    0 //itemspacing
                                ))
                                .models(carouselItems)
                                .numViewsToShowOnScreen(1.35f)
                                .addTo(this)
                        }

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