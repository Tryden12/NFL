package com.tryden.simplenfl.ui.epoxy.models.article

import android.view.View
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelArticleHeaderImageBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

data class ArticleHeaderImageEpoxyModel(
    val headerImage: String
): ViewBindingKotlinModel<ModelArticleHeaderImageBinding>
    (R.layout.model_article_header_image) {

    override fun ModelArticleHeaderImageBinding.bind() {
        if (headerImage.isEmpty()) {
            headerImageView.visibility = View.GONE
        } else {
            Picasso.get().load(headerImage).into(headerImageView)
        }
    }
}