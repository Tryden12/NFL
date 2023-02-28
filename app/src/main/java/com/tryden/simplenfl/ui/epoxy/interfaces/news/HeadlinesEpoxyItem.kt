package com.tryden.simplenfl.ui.epoxy.interfaces.news

import com.tryden.simplenfl.domain.models.news.Headline

interface HeadlinesEpoxyItem {

    data class HeaderItem(val headerTitle: String): HeadlinesEpoxyItem
    data class HeadlineItem(val headline: Headline): HeadlinesEpoxyItem
    object FooterItem: HeadlinesEpoxyItem
    object Spacer: HeadlinesEpoxyItem
}