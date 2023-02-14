package com.tryden.simplenfl.epoxy.models.scores

import android.view.View
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelScoresFinalItemBinding
import com.tryden.simplenfl.epoxy.interfaces.events.EventEntity

data class ScoresCompletedEpoxyModel(
    val eventItemEntity: EventEntity.Completed
//    val logoAway: String,
//    val logoHome: String,
//    val teamNameAway: String,
//    val teamNameHome: String,
//    val pointsAway: String,
//    val pointsHome: String,
//    val datePlayed: String,
//    val statusDesc: String,
//    val headline: String = "",
): ViewBindingKotlinModel<ModelScoresFinalItemBinding>
    (R.layout.model_scores_final_item) {

    override fun ModelScoresFinalItemBinding.bind() {
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

        // Points
        pointsAwayItemTextview.text = eventItemEntity.scoreAway
        pointsHomeItemTextview.text = eventItemEntity.scoreHome

        if (eventItemEntity.scoreAway.toInt() > eventItemEntity.scoreHome.toInt()) {
            winnerArrowAwayImageView.visibility = View.VISIBLE
            winnerArrowHomeImageView.visibility = View.INVISIBLE

            pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.grey))

            teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.grey))

            datePostGameItemTextview.text = eventItemEntity.datePlayed


        } else if (eventItemEntity.scoreAway.toInt() < eventItemEntity.scoreHome.toInt()) {
            winnerArrowAwayImageView.visibility = View.INVISIBLE
            winnerArrowHomeImageView.visibility = View.VISIBLE

            pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.grey))
            pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))

            teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.grey))
            teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))

            datePostGameItemTextview.text = eventItemEntity.datePlayed

        } else { // cancelled or postponed
            winnerArrowAwayImageView.visibility = View.INVISIBLE
            winnerArrowHomeImageView.visibility = View.INVISIBLE

            pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.grey))
            pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.grey))

            teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))

            datePostGameItemTextview.visibility = View.INVISIBLE
        }

        // Status TextView
        statusGameItemTextview.text = eventItemEntity.statusDesc
    }

}