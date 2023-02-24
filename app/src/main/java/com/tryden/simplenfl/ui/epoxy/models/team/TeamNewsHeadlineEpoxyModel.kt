package com.tryden.simplenfl.ui.epoxy.models.team

import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelNewsBreakingHeadlineItemBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

// Headline items
data class TeamNewsHeadlineEpoxyModel(
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