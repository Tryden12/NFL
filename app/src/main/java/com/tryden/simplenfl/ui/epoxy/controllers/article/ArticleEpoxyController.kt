package com.tryden.simplenfl.ui.epoxy.controllers.article

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.domain.models.article.Article
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.article.*

class ArticleEpoxyController: TypedEpoxyController<Article>() {


    override fun buildModels(article: Article?) {
        if (article!!.headline.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        // Title (headline)
        ArticleTitleEpoxyModel(title = article.headline)
            .id("title").addTo(this)

        // Header image
        ArticleHeaderImageEpoxyModel(headerImage = article.headerImage)
            .id("header-image").addTo(this)

        // Author
        ArticleAuthorEpoxyModel(
            authorImage = article.authorImage,
            name = article.authorName,
            source = article.source,
            date = article.date
        ).id("author").addTo(this)

        // Divider
        ArticleDividerEpoxyModel().id("divider").addTo(this)

        // Story
        ArticleStoryEpoxyModel(story = article.story).id("story").addTo(this)

    }
}