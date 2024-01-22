package com.tryden.simplenfl.ui.epoxy.models.news

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelNewsBreakingHeadlineItemBinding
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

// Headline items
data class HeadlineItemEpoxyModel(
    val headline: Headline?,
    val onArticleSelected: (String) -> Unit
): ViewBindingKotlinModel<ModelNewsBreakingHeadlineItemBinding>
    (R.layout.model_news_breaking_headline_item) {

    override fun ModelNewsBreakingHeadlineItemBinding.bind() {

        shimmerLayout.isVisible = headline == null
        constraintLayout.isInvisible = headline == null

        headline?.let {
            shimmerLayout.stopShimmer()

            newsHomeHeadlineShortTitleTextView.text = headline.title

            root.setOnClickListener {
                onArticleSelected(headline.articleId)
            }
        } ?: shimmerLayout.startShimmer()

    }
}