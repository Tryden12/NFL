package com.tryden.simplenfl.epoxy.controllers.news.home.topheadlines

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelNewsBreakingHeadlineItemBinding
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.news.NewsResponse

class HomeTopHeadlinesEpoxyController: EpoxyController() {

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

    // default to 5 headlines
    var maxHeadlines: Int = 5
        set(value) {
            field = value
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
        ).id("home_top_headlines").addTo(this)

        var storyCount = 1
        for (i in newsResponse!!.articles.indices) {
            // article type must NOT be media
            if (!newsResponse!!.articles[i].type.equals("Media", ignoreCase = true)
                && storyCount <= maxHeadlines
            ) {
                HomeNewsHeadlineItemEpoxyModel(
                    headlineTitle = newsResponse!!.articles[i].headline
                ).id("news-$storyCount").addTo(this)
                storyCount++
            }
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
    data class HomeNewsHeadlineItemEpoxyModel(
        val headlineTitle: String
    ): ViewBindingKotlinModel<ModelNewsBreakingHeadlineItemBinding>
        (R.layout.model_news_breaking_headline_item) {

        override fun ModelNewsBreakingHeadlineItemBinding.bind() {
            newsHomeHeadlineShortTitleTextView.text = headlineTitle
        }
    }
}