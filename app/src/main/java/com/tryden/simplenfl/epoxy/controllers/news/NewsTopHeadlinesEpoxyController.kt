package com.tryden.simplenfl.epoxy.controllers.news

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelNewsBreakingHeadlineItemBinding
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.news.NewsResponse

class NewsTopHeadlinesEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var newsResponse: NewsResponse? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (newsResponse == null) {
            // todo error state
            return
        }

        SectionHeaderHeadlinesEpoxyModel(
            title = "Top Headlines",
            logoVisible = true
        ).id("news_top_headlines").addTo(this)

        for (i in newsResponse!!.articles.indices) {
            NewsHeadlineItemEpoxyModel(
                headlineTitle = newsResponse!!.articles[i].headline
            ).id("news-$i").addTo(this)
        }
    }

    // Section header for headlines
    data class SectionHeaderHeadlinesEpoxyModel(
        val title: String,
        val logoVisible: Boolean,
    ): ViewBindingKotlinModel<ModelSectionHeaderBinding>
        (R.layout.model_section_header) {

        override fun ModelSectionHeaderBinding.bind() {
            titleSectionTextView.text = title

            // If logo present
            if (logoVisible)
                logoSectionImageView.visibility = View.VISIBLE
            else
                logoSectionImageView.visibility = View.GONE
        }
    }

    // Headline items
    data class NewsHeadlineItemEpoxyModel(
        val headlineTitle: String
    ): ViewBindingKotlinModel<ModelNewsBreakingHeadlineItemBinding>
        (R.layout.model_news_breaking_headline_item) {

        override fun ModelNewsBreakingHeadlineItemBinding.bind() {
            newsHomeHeadlineShortTitleTextView.text = headlineTitle
        }
    }
}