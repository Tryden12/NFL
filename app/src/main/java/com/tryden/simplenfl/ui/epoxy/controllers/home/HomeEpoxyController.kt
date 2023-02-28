package com.tryden.simplenfl.ui.epoxy.controllers.home

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.ui.epoxy.interfaces.news.HeadlinesEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.SectionBottomEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.news.HeadlineItemEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel

class HomeEpoxyController(
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
                        logoVisible = true,
                        usePlaceholderLogo = true,
                        logoUrl = ""
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
                    SectionBottomEpoxyModel().id("footer-headlines").addTo(this)
                }
                is HeadlinesEpoxyItem.Spacer -> {
                    // do nothing
                }
            }
        }
    }
}