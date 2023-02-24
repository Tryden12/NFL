package com.tryden.simplenfl.ui.epoxy.controllers.team.news

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.ui.epoxy.interfaces.team.TeamNewsEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.SectionBottomEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel2
import com.tryden.simplenfl.ui.epoxy.models.team.TeamNewsHeadlineEpoxyModel

class TeamNewsEpoxyController(
    private val onArticleSelected: (String) -> Unit
): TypedEpoxyController<List<TeamNewsEpoxyItem>>() {


    override fun buildModels(items: List<TeamNewsEpoxyItem>) {
        if (items.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        items.forEachIndexed { index, item ->
            when (item) {
                is TeamNewsEpoxyItem.HeaderItem -> {
                    SectionHeaderEpoxyModel2(
                        title = item.header,
                        logo = item.logo,
                        logoVisible = true
                    ).id("header").addTo(this)
                }
                is TeamNewsEpoxyItem.HeadlineItem -> {
                    TeamNewsHeadlineEpoxyModel(
                        headlineTitle = item.headline.articleHeadline,
                        articleId = item.headline.articleId,
                        onArticleSelected = onArticleSelected
                    ).id(index).addTo(this)
                }
                is TeamNewsEpoxyItem.FooterItem -> {
                    SectionBottomEpoxyModel(useSection = true)
                        .id("bottom").addTo(this)
                }
            }
        }
    }
}