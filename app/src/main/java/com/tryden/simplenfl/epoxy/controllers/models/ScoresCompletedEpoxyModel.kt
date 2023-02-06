package com.tryden.simplenfl.epoxy.controllers.models

import android.view.View
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelScoresFinalItemBinding
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

data class ScoresCompletedEpoxyModel(
    val logoAway: String,
    val logoHome: String,
    val teamNameAway: String,
    val teamNameHome: String,
    val pointsAway: String,
    val pointsHome: String,
    val datePlayed: String,
    val statusDesc: String,
    val headline: String = "",
): ViewBindingKotlinModel<ModelScoresFinalItemBinding>
    (R.layout.model_scores_final_item) {

    override fun ModelScoresFinalItemBinding.bind() {
        // Logos
        if (logoAway.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_logo)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(awayLogoImageView)
        } else {
            Picasso.get().load(logoAway).into(awayLogoImageView)
        }

        if (logoHome.isEmpty()) {
            Picasso.get()
                .load(R.drawable.placeholder_logo)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(homeLogoImageView)
        } else {
            Picasso.get().load(logoHome).into(homeLogoImageView)
        }

        // Team names
        teamNameAwayTextview.text = teamNameAway
        teamNameHomeTextview.text = teamNameHome

        // Game headline (bottom)
        if (headline.isEmpty()) {
            descriptionGameBottomItemTextview.visibility = View.GONE
        } else {
            descriptionGameBottomItemTextview.visibility = View.VISIBLE
            descriptionGameBottomItemTextview.text = headline
        }

        // Points
        pointsAwayItemTextview.text = pointsAway
        pointsHomeItemTextview.text = pointsHome

        // Parse ISO format to "E, M/d"
        val actual = OffsetDateTime.parse(datePlayed, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("E',' M/d")
        val formatDateTime = actual.format(formatter)

        if (pointsAway.toInt() > pointsHome.toInt()) {
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

            datePostGameItemTextview.text = formatDateTime.toString()


        } else if (pointsAway.toInt() < pointsHome.toInt()) {
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

            datePostGameItemTextview.text = formatDateTime.toString()

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
        statusGameItemTextview.text = statusDesc
    }

}