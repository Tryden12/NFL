package com.tryden.simplenfl.epoxy.controllers.team.scores

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelScoresFinalItemBinding
import com.tryden.simplenfl.databinding.ModelScoresScheduledItemBinding
import com.tryden.simplenfl.databinding.ModelScoresSeasonTypeHeaderBinding
import com.tryden.simplenfl.databinding.ModelScoresSeasonTypeHeaderBottomBinding
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TeamScoresEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
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
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (scoresResponse == null) {
            // todo error state
            return
        }

        var postHeaderFilled = false
        var regularHeaderFilled = false

//        SeasonTypeHeader(seasonType = "Post Season")
//            .id("post-season").addTo(this)

        for (i in scoresResponse!!.events.size-1 downTo 0) {
            if (scoresResponse!!.events[i].competitions[0].status.type.state == "pre") {

                for (j in scoresResponse!!.events[i].competitions[0].competitors.indices) {
                    if (scoresResponse!!.events[i].competitions[0].competitors[j].team.id == "2") {

                        if (!postHeaderFilled) {
                            SeasonTypeHeader(seasonType = "Post Season")
                                .id("post-season").addTo(this)
                            postHeaderFilled = true
                        }
                        ScoresScheduledItemEpoxyModel(
                            logoAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            logoHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.name.toString(),
                            teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.name.toString(),
                            recordAway = scoresResponse!!.events[i].competitions[0].competitors[0].records?.get(0)?.summary.toString(),
                            recordHome = scoresResponse!!.events[i].competitions[0].competitors[1].records?.get(0)?.summary.toString(),
                            dateScheduled = scoresResponse!!.events[i].date,
                            broadcast = scoresResponse!!.events[i].competitions[0].geoBroadcasts[0].media.shortName,
                            headline = scoresResponse!!.events[i].competitions[0].notes[0].headline
                        ).id(scoresResponse!!.events[i].id).addTo(this)
                    }
                }
            }
        }
        if (postHeaderFilled) {
            SeasonTypeHeaderBottom(filler = "").id("1").addTo(this)
        }



//        SeasonTypeHeader(seasonType = "Regular Season")
//            .id("regular-season").addTo(this)
//

        for (i in scoresResponse!!.events.size-1 downTo 0) {
//        for (i in scoresResponse!!.events.indices) {
            if (scoresResponse!!.events[i].competitions[0].status.type.state == "post") {

                for (j in scoresResponse!!.events[i].competitions[0].competitors.indices) {
                    if (scoresResponse!!.events[i].competitions[0].competitors[j].team.id == "2") {

                        if (!regularHeaderFilled) {
                            SeasonTypeHeader(seasonType = "Regular Season")
                                .id("regular-season").addTo(this)
                            regularHeaderFilled = true
                        }
                        ScoresFinalItemEpoxyModel(
                            logoAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo.toString(),
                            logoHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo.toString(),
                            teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.name.toString(),
                            teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.name.toString(),
                            pointsAway = scoresResponse!!.events[i].competitions[0].competitors[0].score,
                            pointsHome = scoresResponse!!.events[i].competitions[0].competitors[1].score,
                            datePlayed = scoresResponse!!.events[i].date,
                            statusDesc = scoresResponse!!.events[i].competitions[0].status.type.description
                        ).id(scoresResponse!!.events[i].id).addTo(this)
                    }
                }
            }
        }
        if (regularHeaderFilled) {
            SeasonTypeHeaderBottom(filler = "").id("2").addTo(this)
        }
    }

    // Add season type header
    data class SeasonTypeHeader(
        val seasonType: String,
    ): ViewBindingKotlinModel<ModelScoresSeasonTypeHeaderBinding>(R.layout.model_scores_season_type_header) {

        override fun ModelScoresSeasonTypeHeaderBinding.bind() {
            seasonTypeTextview.text = seasonType
        }
    }

    data class SeasonTypeHeaderBottom(
        val filler: String
    ): ViewBindingKotlinModel<ModelScoresSeasonTypeHeaderBottomBinding>(R.layout.model_scores_season_type_header_bottom) {

        override fun ModelScoresSeasonTypeHeaderBottomBinding.bind() {
           // nothing to do
        }
    }

    // Add scores item
    data class ScoresScheduledItemEpoxyModel(
        val logoAway: String,
        val logoHome: String,
        val teamNameAway: String,
        val teamNameHome: String,
        val recordAway: String,
        val recordHome: String,
        val dateScheduled: String,
        val broadcast: String,
        val headline: String,
    ): ViewBindingKotlinModel<ModelScoresScheduledItemBinding>(R.layout.model_scores_scheduled_item) {

        override fun ModelScoresScheduledItemBinding.bind() {

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

            teamNameAwayTextview.text = teamNameAway
            teamNameHomeTextview.text = teamNameHome

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

            recordAwayItemTextview.text = recordAway
            recordHomeItemTextview.text = recordHome
            datePreGameItemTextview.text = formatDate
            timePreGameItemTextview.text = gameTime
            broadcastPreGameItemTextview.text = broadcast



        }
    }

    // Add scores item
    data class ScoresFinalItemEpoxyModel(
        val logoAway: String,
        val logoHome: String,
        val teamNameAway: String,
        val teamNameHome: String,
        val pointsAway: String,
        val pointsHome: String,
        val datePlayed: String,
        val statusDesc: String,
        val headline: String = "",
    ): ViewBindingKotlinModel<ModelScoresFinalItemBinding>(R.layout.model_scores_final_item) {

        @SuppressLint("SimpleDateFormat")
        override fun ModelScoresFinalItemBinding.bind() {

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

            teamNameAwayTextview.text = teamNameAway
            teamNameHomeTextview.text = teamNameHome

            if (headline.isEmpty()) {
                descriptionGameBottomItemTextview.visibility = View.GONE
            } else {
                descriptionGameBottomItemTextview.visibility = View.VISIBLE
                descriptionGameBottomItemTextview.text = headline
            }

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
