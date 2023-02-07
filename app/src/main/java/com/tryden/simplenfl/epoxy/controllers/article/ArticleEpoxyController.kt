package com.tryden.simplenfl.epoxy.controllers.article

import android.os.Build
import android.text.Html
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelArticleAuthorBinding
import com.tryden.simplenfl.databinding.ModelArticleHeaderImageBinding
import com.tryden.simplenfl.databinding.ModelArticleStoryBinding
import com.tryden.simplenfl.databinding.ModelArticleTitleBinding
import com.tryden.simplenfl.databinding.ModelDividerSolidGreyBinding
import com.tryden.simplenfl.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.article.ArticleResponse
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ArticleEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var articleResponse: ArticleResponse? = null
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

        if (articleResponse == null) {
            // todo error state
            return
        }

        // Title
        ArticleTitleEpoxyModel(
            title = articleResponse!!.headlines[0].headline
        ).id("article-title").addTo(this)

        // Header image
        ArticleHeaderImageEpoxyModel(
            headerImage = articleResponse!!.headlines[0].images[0].url
        ).id("article-header-image").addTo(this)

        // Author
        ArticleAuthorEpoxyModel(
            authorImage = "",
            name = articleResponse!!.headlines[0].byline,
            source = "ESPN",
            date = articleResponse!!.headlines[0].published
        ).id("article-author").addTo(this)

        // Divider
        ArticleDividerEpoxyModel(
            divider = true
        ).id("article-divider").addTo(this)

        // Story
        ArticleStoryEpoxyModel(
            story = addBrTags(articleResponse!!.headlines[0].story)
        ).id("article-story").addTo(this)

    }

    /**
     * Parse through the html and add <br> tags after every closing </p>
     * to add a gap between paragraphs
     */
    private fun addBrTags(html: String) : StringBuilder {
        var builder = StringBuilder()
        builder.append(html)
        for (i in builder.indices) {
            if(builder[i] == 'p' && builder[i-1] == '/') {
                builder.insert(i+2, "<br>")
            }
        }
        return builder
    }

    data class ArticleTitleEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelArticleTitleBinding>
        (R.layout.model_article_title) {

        override fun ModelArticleTitleBinding.bind() {
            titleTextView.text = title
        }
    }

    data class ArticleHeaderImageEpoxyModel(
        val headerImage: String
    ):ViewBindingKotlinModel<ModelArticleHeaderImageBinding>
        (R.layout.model_article_header_image) {

        override fun ModelArticleHeaderImageBinding.bind() {
            if (headerImage.isEmpty()) {
                headerImageView.visibility = View.GONE
            } else {
                Picasso.get().load(headerImage).into(headerImageView)
            }
        }
    }

    data class ArticleAuthorEpoxyModel(
        val authorImage: String,
        val name: String,
        val source: String,
        val date: String
    ): ViewBindingKotlinModel<ModelArticleAuthorBinding>
        (R.layout.model_article_author) {

        override fun ModelArticleAuthorBinding.bind() {

            // Author image
            if (authorImage.isEmpty()) {
                Picasso.get()
                    .load(R.drawable.ic_author_24)
                    .placeholder(R.drawable.ic_author_24)
                    .error(R.drawable.ic_author_24)
                    .into(authorImageView)
            } else {
                Picasso.get().load(authorImage).into(authorImageView)
            }

            authorNameTextView.text = name
            sourceTextView.text = source

            // Format date to "4:30 PM"
            val actual = OffsetDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
            val formatter = DateTimeFormatter.ofPattern("h:mm a")
            val publishedDate = actual.format(formatter)

            dateTextView.text = publishedDate
        }
    }

    data class ArticleDividerEpoxyModel(
        val divider: Boolean
    ): ViewBindingKotlinModel<ModelDividerSolidGreyBinding>
        (R.layout.model_divider_solid_grey) {

        override fun ModelDividerSolidGreyBinding.bind() {
            if (!divider) {
                dividerView.visibility = View.GONE
            }
        }
    }

    data class ArticleStoryEpoxyModel(
        val story: StringBuilder
    ): ViewBindingKotlinModel<ModelArticleStoryBinding>
        (R.layout.model_article_story) {

        override fun ModelArticleStoryBinding.bind() {
            if (story.isEmpty()) {
                storyTextView.text = ""
            } else {
                storyTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(story.toString(), Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(story.toString())
                }
            }
        }
    }
}