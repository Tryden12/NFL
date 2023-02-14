package com.tryden.simplenfl.epoxy.models.scores

import android.view.View
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelScoresScheduledItemBinding
import com.tryden.simplenfl.epoxy.interfaces.events.EventEntity

data class ScoresUpcomingEpoxyModel(
    val eventItemEntity: EventEntity.Upcoming
//    val logoAway: String,
//    val logoHome: String,
//    val teamNameAway: String,
//    val teamNameHome: String,
//    val recordAway: String,
//    val recordHome: String,
//    val dateScheduled: String,
//    val gameTime: String,
//    val broadcast: String,
//    val headline: String
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