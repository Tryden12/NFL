package com.tryden.simplenfl.epoxy.controllers.news

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelNewsBreakingHeadlineItemBinding
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.news.home.topheadlines.HomeTopHeadlinesEpoxyController
import com.tryden.simplenfl.network.response.teams.models.news.NewsResponse

class NewsTopHeadlinesEpoxyController(
    private val onArticleSelected: (String) -> Unit
): EpoxyController() {

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
        ).id("news_top_headlines").addTo(this)


        var storyCount = 1
        for (i in newsResponse!!.articles.indices) {
            // article type must NOT be media
            if (newsResponse!!.articles[i].type.equals("HeadlineNews", ignoreCase = true)
                && storyCount <= maxHeadlines
            ) {
                getArticleIdFromUrl(newsResponse!!.articles[i].links.api.news.href)?.let { articleId ->
                    NewsHeadlineItemEpoxyModel(
                        headlineTitle = newsResponse!!.articles[i].headline,
                        articleId = articleId,
                        onArticleSelected = onArticleSelected
                    ).id("news-$storyCount").addTo(this)
                }
                storyCount++
            }
        }
    }

    /**
     * To navigate on click from headline to view the article,
     * we must grab the article id in the given url.
     *
     * This article id is then passed in through this data flow:
     * EpoxyModel -> private fun in Fragment -> SharedViewModel
     *
     * Then the ArticleFragment grabs the article id from SharedViewModel
     */
    private fun getArticleIdFromUrl(url: String?): String? {
        return url?.split("sports/news/")?.get(1)
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
        val headlineTitle: String,
        val articleId: String,
        val onArticleSelected: (String) -> Unit
    ): ViewBindingKotlinModel<ModelNewsBreakingHeadlineItemBinding>
        (R.layout.model_news_breaking_headline_item) {

        override fun ModelNewsBreakingHeadlineItemBinding.bind() {
            newsHomeHeadlineShortTitleTextView.text = headlineTitle

            root.setOnClickListener {
                onArticleSelected(articleId)
            }
        }
    }
}