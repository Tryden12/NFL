package com.tryden.simplenfl.ui.epoxy.models.news

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelCarouselMyNewsItemBinding
import com.tryden.simplenfl.domain.models.news.FavoriteHeadline
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

data class MyNewsCarouselEpoxyItem(
    val newsItem: FavoriteHeadline,
    val onArticleSelected: (String) -> Unit
): ViewBindingKotlinModel<ModelCarouselMyNewsItemBinding>(R.layout.model_carousel_my_news_item) {

    override fun ModelCarouselMyNewsItemBinding.bind() {

        // On click listener
        root.setOnClickListener {
            onArticleSelected(newsItem.articleId)
        }

        // Article image
        Picasso.get().load(newsItem.articleImage).into(imageImageView)

        // Divider color
        val teamColor = "#${newsItem.teamColor}"
        dividerView.background =  ColorDrawable(Color.parseColor(teamColor))

        // Logo
        if (newsItem.teamLogo.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_logo)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(logoImageView)
        } else {
            Picasso.get().load(newsItem.teamLogo).into(logoImageView)
        }

        // Team name
        teamNameTextView.text = newsItem.teamName

        // Headline title
        titleTextView.text = newsItem.headline

        // Published
        publishedTextView.text = newsItem.timeSincePosted.ifEmpty { "3:00 PM" }

        // Author
        authorTextView.text = newsItem.author.ifEmpty { "Adam Schefter" }
    }
}