package com.tryden.simplenfl.ui.epoxy.controllers.news

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionFooterEpoxyModel
import com.tryden.simplenfl.ui.epoxy.interfaces.news.HeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.news.HeadlineItemEpoxyModel

class NewsEpoxyController(
    private val onArticleSelected: (String) -> Unit
): TypedEpoxyController<List<HeadlinesEpoxyItem>>() {


    override fun buildModels(items: List<HeadlinesEpoxyItem>) {
        if (items.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        items.forEach { item ->
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
                        headline = item.headline,
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
    }
}