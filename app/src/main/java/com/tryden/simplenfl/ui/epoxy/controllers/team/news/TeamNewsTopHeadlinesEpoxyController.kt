package com.tryden.simplenfl.ui.epoxy.controllers.team.news

import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelNewsBreakingHeadlineItemBinding
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionBottomEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel
import com.tryden.simplenfl.network.response.models.news.NewsResponse
import com.tryden.simplenfl.network.response.models.team.TeamObjectResponse

class TeamNewsTopHeadlinesEpoxyController(
    private val onArticleSelected: (String) -> Unit
): EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var teamNewsResponse: com.tryden.simplenfl.network.response.models.news.NewsResponse? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    var teamDetailsResponse: com.tryden.simplenfl.network.response.models.team.TeamObjectResponse? = null
        set(value) {
            field = value
        }

    // default to 5 headlines
    var maxHeadlines: Int = 8
        set(value) {
            field = value
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (teamNewsResponse == null) {
            // todo error state
            return
        }

        /**
         * Headline News
         **/
        var headerTopFilled = false
        var storyCount = 1
        for (i in teamNewsResponse!!.articles.indices) {
            // article type must NOT be media
            if (teamNewsResponse!!.articles[i].type.equals("HeadlineNews", ignoreCase = true)
                && storyCount <= maxHeadlines
            ) {

                // Use header
                if (storyCount == 1) {
                    SectionHeaderEpoxyModel(
                        title = "Top Headlines",
                        logoVisible = true,
                        usePlaceholderLogo = false,
                        /** Use Team Logo **/ /** Use Team Logo **/
                        logoUrl = teamDetailsResponse!!.team.logos[0].href
                    ).id("team_top_headlines").addTo(this)
                    headerTopFilled = true
                }

                getArticleIdFromUrl(teamNewsResponse!!.articles[i].links.api.news.href)?.let { articleId ->
                    TeamNewsHeadlineItemEpoxyModel(
                        headlineTitle = teamNewsResponse!!.articles[i].headline,
                        articleId = articleId,
                        onArticleSelected = onArticleSelected
                    ).id("news-$storyCount").addTo(this)
                }
                storyCount++
            }
        }
        // Add bottom to section
        if (headerTopFilled) {
            SectionBottomEpoxyModel(
                useSection = true
            ).id("bottom-playoffs-1").addTo(this)
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

    // Headline items
    data class TeamNewsHeadlineItemEpoxyModel(
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