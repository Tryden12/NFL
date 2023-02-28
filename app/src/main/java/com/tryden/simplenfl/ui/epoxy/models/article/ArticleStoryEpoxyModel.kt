package com.tryden.simplenfl.ui.epoxy.models.article

import android.os.Build
import android.text.Html
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelArticleStoryBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

data class ArticleStoryEpoxyModel(
    val story: String
): ViewBindingKotlinModel<ModelArticleStoryBinding>
    (R.layout.model_article_story) {

    override fun ModelArticleStoryBinding.bind() {
        if (story.isEmpty()) {
            storyTextView.text = ""
        } else {
            storyTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(story, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(story)
            }
        }
    }
}