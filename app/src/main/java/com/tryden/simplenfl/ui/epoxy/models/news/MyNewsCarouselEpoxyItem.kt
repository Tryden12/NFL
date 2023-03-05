package com.tryden.simplenfl.ui.epoxy.models.news

import com.squareup.picasso.Picasso
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelCarouselMyNewsItemBinding
import com.tryden.simplenfl.domain.models.news.FavoriteHeadline
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

data class MyNewsCarouselEpoxyItem(
    val newsItem: FavoriteHeadline
): ViewBindingKotlinModel<ModelCarouselMyNewsItemBinding>(R.layout.model_carousel_my_news_item) {

    override fun ModelCarouselMyNewsItemBinding.bind() {
        // Article image
        Picasso.get().load(newsItem.articleImage).into(imageImageView)


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
        publishedTextView.text = newsItem.timeSincePosted

        // Author
        authorTextView.text = newsItem.author
    }
}