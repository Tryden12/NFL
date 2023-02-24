package com.tryden.simplenfl.ui.epoxy.interfaces.team

import com.tryden.simplenfl.domain.models.team.ArticleHeadline


interface TeamNewsEpoxyItem {

    data class HeaderItem(val header: String, val logo: String): TeamNewsEpoxyItem
    data class HeadlineItem(val headline: ArticleHeadline): TeamNewsEpoxyItem
    object FooterItem: TeamNewsEpoxyItem
    object Spacer: TeamNewsEpoxyItem
}