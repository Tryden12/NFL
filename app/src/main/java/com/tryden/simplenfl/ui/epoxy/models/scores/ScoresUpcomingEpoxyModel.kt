package com.tryden.simplenfl.ui.epoxy.models.scores

import android.view.View
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelScoresScheduledItemBinding
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity.Upcoming

data class ScoresUpcomingEpoxyModel(
    val eventItemEntity: Upcoming
): ViewBindingKotlinModel<ModelScoresScheduledItemBinding>
    (R.layout.model_scores_scheduled_item) {


    override fun ModelScoresScheduledItemBinding.bind() {


        // Logos
        if (eventItemEntity.awayTeam.logo!!.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_logo)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(awayLogoImageView)
        } else {
            Picasso.get().load(eventItemEntity.awayTeam.logo).into(awayLogoImageView)
        }

        if (eventItemEntity.homeTeam.logo!!.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_logo)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(homeLogoImageView)
        } else {
            Picasso.get().load(eventItemEntity.homeTeam.logo).into(homeLogoImageView)
        }

        // Team names
        when (eventItemEntity.awayTeam.name.isEmpty()) {
            true -> teamNameAwayTextview.text = "TBD"
            false -> teamNameAwayTextview.text = eventItemEntity.awayTeam.name
        }

        when (eventItemEntity.homeTeam.name.isEmpty()) {
            true -> teamNameHomeTextview.text = "TBD"
            false -> teamNameHomeTextview.text = eventItemEntity.homeTeam.name
        }


        // Game headline (bottom)
        if (eventItemEntity.headline.isEmpty()) {
            descriptionGameBottomItemTextview.visibility = View.GONE
        } else {
            descriptionGameBottomItemTextview.visibility = View.VISIBLE
            descriptionGameBottomItemTextview.text = eventItemEntity.headline
        }


        // Right side of item
        recordAwayItemTextview.text = eventItemEntity.recordAway
        recordHomeItemTextview.text = eventItemEntity.recordHome
        datePreGameItemTextview.text = eventItemEntity.dateScheduled
        timePreGameItemTextview.text = eventItemEntity.gameTime
        broadcastPreGameItemTextview.text = eventItemEntity.broadcast

    }
}