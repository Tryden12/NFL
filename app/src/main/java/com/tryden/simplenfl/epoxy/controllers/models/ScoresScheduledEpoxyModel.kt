package com.tryden.simplenfl.epoxy.controllers.models

import android.view.View
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelScoresScheduledItemBinding
import com.tryden.simplenfl.databinding.ModelScoresScheduledWithHeaderItemBinding
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class ScoresScheduledEpoxyModel(
    val logoAway: String,
    val logoHome: String,
    val teamNameAway: String,
    val teamNameHome: String,
    val recordAway: String,
    val recordHome: String,
    val dateScheduled: String,
    val broadcast: String,
    val headline: String,
): ViewBindingKotlinModel<ModelScoresScheduledItemBinding>
    (R.layout.model_scores_scheduled_item) {

    override fun ModelScoresScheduledItemBinding.bind() {

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
        when (teamNameAway.isEmpty()) {
            true -> teamNameAwayTextview.text = "TBD"
            false -> teamNameAwayTextview.text = teamNameAway
        }

        when (teamNameHome.isEmpty()) {
            true -> teamNameHomeTextview.text = "TBD"
            false -> teamNameHomeTextview.text = teamNameHome
        }


        // Game headline (bottom)
        if (headline.isEmpty()) {
            descriptionGameBottomItemTextview.visibility = View.GONE
        } else {
            descriptionGameBottomItemTextview.visibility = View.VISIBLE
            descriptionGameBottomItemTextview.text = headline
        }

        // Parse ISO format to "Sun, 10/18"
        val responseDate = OffsetDateTime
            .parse(dateScheduled, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter = DateTimeFormatter.ofPattern("E',' M/d")
        val formatDate = responseDate.format(formatter)

        // Parse ISO format to "4:30 PM"
        val actual = OffsetDateTime
            .parse(dateScheduled, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter2 = DateTimeFormatter.ofPattern("h:mm a")
        val gameTime = actual.format(formatter2)

        // Right side of item
        recordAwayItemTextview.text = recordAway
        recordHomeItemTextview.text = recordHome
        datePreGameItemTextview.text = formatDate
        timePreGameItemTextview.text = gameTime
        broadcastPreGameItemTextview.text = broadcast

    }
}