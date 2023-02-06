package com.tryden.simplenfl.epoxy.controllers.scores.home

import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelScoresFinalWithHeaderItemBinding
import com.tryden.simplenfl.databinding.ModelScoresScheduledWithHeaderItemBinding
import com.tryden.simplenfl.epoxy.controllers.models.ScoresUpcomingEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.models.SectionBottomEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.models.SectionHeaderCenteredEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class HomeScoresEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value){
            field = value
            if (value) {
                requestModelBuild()
            }
        }

    var scoresResponse: ScoreboardResponse? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
//            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (scoresResponse == null) {
            // todo error state
            return
        }

        /**
         * Upcoming games
         **/
        var headerTopFilled = false
        var scheduledCount = 0
//        for (i in scoresResponse!!.events.size-1 downTo 0) { // reverse
        for (i in scoresResponse!!.events.indices) {

            when (scoresResponse!!.events[i].competitions[0].status.type.state) {
                "pre" -> {
                    if (scoresResponse!!.events[i].competitions[0].notes[0].headline.contains("pro bowl", ignoreCase = true)
                    ) {

                        // Use header
                        if (scheduledCount == 0) {
                            SectionHeaderCenteredEpoxyModel(
                                sectionHeader = "Upcoming"
                            ).id("header-playoffs-$i").addTo(this)
                            headerTopFilled = true
                        }

                        // Add scores upcoming item
                        ScoresUpcomingEpoxyModel(
                            logoAway = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            logoHome = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
                            teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
                            recordAway = "",
                            recordHome = "",
                            dateScheduled = scoresResponse!!.events[i].date,
                            broadcast = scoresResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
                            headline = scoresResponse!!.events[i].competitions[0].notes[0].headline
                        ).id(scoresResponse!!.events[i].id).addTo(this)
                        scheduledCount++

                    } else {

                        // Use header
                        if (scheduledCount == 0) {
                            SectionHeaderCenteredEpoxyModel(
                                sectionHeader = "Upcoming"
                            ).id("header-playoffs-$i").addTo(this)
                            headerTopFilled = true
                        }

                        // Add scores upcoming item
                        ScoresUpcomingEpoxyModel(
                            logoAway = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            logoHome = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[1].team.shortDisplayName,
                            teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[0].team.shortDisplayName,
                            recordAway = scoresResponse!!.events[i].competitions[0].competitors[1].records?.get(0)?.summary.toString(),
                            recordHome = scoresResponse!!.events[i].competitions[0].competitors[0].records?.get(0)?.summary.toString(),
                            dateScheduled = scoresResponse!!.events[i].date,
                            broadcast = scoresResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
                            headline = scoresResponse!!.events[i].competitions[0].notes[0].headline
                        ).id(scoresResponse!!.events[i].id).addTo(this)
                        scheduledCount++
                    }
                }
            }
        }

        // Add bottom to section
        if (headerTopFilled) {
            SectionBottomEpoxyModel(
                useSection = true
            ).id("bottom-home-upcoming-1").addTo(this)
        }
        
    }

    // Scores
    data class ScoresScheduledEpoxyController(
        val sectionHeader: String,
        val useSectionHeader: Boolean,
        val logoAway: String,
        val logoHome: String,
        val teamNameAway: String,
        val teamNameHome: String,
        val recordAway: String,
        val recordHome: String,
        val dateScheduled: String,
        val broadcast: String,
        val headline: String,
        ): ViewBindingKotlinModel<ModelScoresScheduledWithHeaderItemBinding>
        (R.layout.model_scores_scheduled_with_header_item) {

        override fun ModelScoresScheduledWithHeaderItemBinding.bind() {

            // Section header
            if (useSectionHeader) {
                scoresHeaderTextview.visibility = View.VISIBLE
                scoresHeaderTextview.text = sectionHeader
            } else {
                scoresHeaderTextview.visibility = View.GONE
            }

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
            val responseDate = OffsetDateTime.parse(dateScheduled, DateTimeFormatter.ISO_DATE_TIME)
            val formatter = DateTimeFormatter.ofPattern("E',' M/d")
            val formatDate = responseDate.format(formatter)

            // Parse ISO format to "4:30 PM"
            val actual = OffsetDateTime.parse(dateScheduled, DateTimeFormatter.ISO_DATE_TIME)
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

    data class ScoresFinalHomeEpoxyController(
        val sectionHeader: String,
        val useSectionHeader: Boolean,
        val logoAway: String,
        val logoHome: String,
        val teamNameAway: String,
        val teamNameHome: String,
        val pointsAway: String,
        val pointsHome: String,
        val datePlayed: String,
        val statusDesc: String,
        val headline: String = "",
        ): ViewBindingKotlinModel<ModelScoresFinalWithHeaderItemBinding>
        (R.layout.model_scores_final_with_header_item) {

        override fun ModelScoresFinalWithHeaderItemBinding.bind() {
            // Section header
            if (useSectionHeader) {
                scoresHeaderTextview.visibility = View.VISIBLE
                scoresHeaderTextview.text = sectionHeader
            } else {
                scoresHeaderTextview.visibility = View.GONE
            }


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

                pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))
                pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))

                teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))
                teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))

                datePostGameItemTextview.text = formatDateTime.toString()


            } else if (pointsAway.toInt() < pointsHome.toInt()) {
                winnerArrowAwayImageView.visibility = View.INVISIBLE
                winnerArrowHomeImageView.visibility = View.VISIBLE

                pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))
                pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))

                teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))
                teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))

                datePostGameItemTextview.text = formatDateTime.toString()

            } else { // cancelled or postponed
                winnerArrowAwayImageView.visibility = View.INVISIBLE
                winnerArrowHomeImageView.visibility = View.INVISIBLE

                pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))
                pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))

                teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))
                teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))

                datePostGameItemTextview.visibility = View.INVISIBLE
            }

            // Status TextView
            statusGameItemTextview.text = statusDesc
        }
    }
}