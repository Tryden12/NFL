package com.tryden.simplenfl.ui.epoxy.models.article

import com.squareup.picasso.Picasso
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelArticleAuthorBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

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
                .load(R.drawable.placeholder_author_headshot)
                .placeholder(R.drawable.placeholder_author_headshot)
                .error(R.drawable.placeholder_author_headshot)
                .into(authorImageView)
        } else {
            Picasso.get().load(authorImage).into(authorImageView)
        }

        authorNameTextView.text = name.ifEmpty { "Tyler Ryden" }
        sourceTextView.text = source.ifEmpty { "ESPN" }

        // Format date to "4:30 PM"
        val actual = OffsetDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("h:mm a")
        val publishedDate = actual.format(formatter)

        dateTextView.text = publishedDate
    }
}