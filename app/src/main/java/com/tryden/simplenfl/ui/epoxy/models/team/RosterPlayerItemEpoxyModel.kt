package com.tryden.simplenfl.ui.epoxy.models.team

import com.squareup.picasso.Picasso
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelRosterPlayerItemBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

// Player item model
data class RosterPlayerItemEpoxyModel(
    val imageUrl: String,
    val name: String,
    val number: String,
    val position: String,
    val age: String,
    val height: String,
    val backgroundColor: Int
): ViewBindingKotlinModel<ModelRosterPlayerItemBinding>(R.layout.model_roster_player_item) {

    override fun ModelRosterPlayerItemBinding.bind() {

        parentConstraintLayout.setBackgroundColor(backgroundColor)

        if (imageUrl.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_headshot)
                .placeholder(R.drawable.placeholder_headshot)
                .error(R.drawable.placeholder_headshot)
                .into(playerImageImageView)
        } else {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_headshot)
                .error(R.drawable.placeholder_headshot)
                .into(playerImageImageView)
        }


        nameTextView.text = name
        numberTextView.text = number
        positionTextView.text = position
        ageTextView.text = age
        heightTextView.text = height

    }
}