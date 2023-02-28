package com.tryden.simplenfl.ui.epoxy.models.article

import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelArticleTitleBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

data class ArticleTitleEpoxyModel(
    val title: String
): ViewBindingKotlinModel<ModelArticleTitleBinding>
    (R.layout.model_article_title) {

    override fun ModelArticleTitleBinding.bind() {
        titleTextView.text = title
    }
}