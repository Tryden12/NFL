package com.tryden.simplenfl.ui.epoxy.controllers.team.news

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.ui.epoxy.interfaces.team.TeamNewsEpoxyItem
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel2
import com.tryden.simplenfl.ui.epoxy.models.team.TeamNewsHeadlineEpoxyModel

class TeamNewsEpoxyController(
    private val onArticleSelected: (String) -> Unit
): TypedEpoxyController<List<TeamNewsEpoxyItem>>() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    // default to 5 headlines
    var maxHeadlines: Int = 8
        set(value) {
            field = value
        }


    override fun buildModels(items: List<TeamNewsEpoxyItem>) {
        if (items.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        Log.e("TeamNewsEpoxyController", "items count: ${items.size}")


        items.forEachIndexed { index, item ->
            when (item) {
                is TeamNewsEpoxyItem.HeaderItem -> {
                    SectionHeaderEpoxyModel2(
                        title = item.header,
                        logo = item.logo,
                        logoVisible = true
                    ).id("header").addTo(this)

                    Log.e("TeamNewsEpoxyController", "logo url : ${item.logo}")

                }
                is TeamNewsEpoxyItem.HeadlineItem -> {
                    TeamNewsHeadlineEpoxyModel(
                        headlineTitle = item.headline.articleHeadline,
                        articleId = item.headline.articleId,
                        onArticleSelected = onArticleSelected
                    ).id(index).addTo(this)

                    Log.e("TeamNewsEpoxyController", "article headline : ${item.headline}")

                }
//                is TeamNewsEpoxyItem.FooterItem -> {
//                    SectionBottomEpoxyModel(useSection = true)
//                        .id(index+1).addTo(this)
//                }
            }
        }
    }

}