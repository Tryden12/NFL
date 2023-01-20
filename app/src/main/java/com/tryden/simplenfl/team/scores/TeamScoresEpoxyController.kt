package com.tryden.simplenfl.team.scores

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelScoresPostItemBinding
import com.tryden.simplenfl.databinding.ModelScoresPreItemBinding
import com.tryden.simplenfl.databinding.ModelScoresSeasonTypeHeaderBinding
import com.tryden.simplenfl.network.response.teams.models.scoreboard.Scoreboard
import java.time.LocalDate
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
    var scoresResponse: Scoreboard? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }


    override fun buildModels() {
        if (isLoading) {
            // Loading model if you so choose
            return
        }

        if (scoresResponse == null) {
            // todo error state
            return
        }

        SeasonTypeHeader(seasonType = "Regular Season")
            .id("regular-season").addTo(this)


        for (i in scoresResponse!!.events.size-1 downTo 0) {
//        for (i in scoresResponse!!.events.indices) {
            if (scoresResponse!!.events[i].competitions[0].status.type.state == "post") {

                for (j in scoresResponse!!.events[i].competitions[0].competitors.indices) {
                    if (scoresResponse!!.events[i].competitions[0].competitors[j].team.id == "2") {
                        ScoresPostItemEpoxyModel(
                            logoAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.logo,
                            logoHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.logo,
                            teamNameAway = scoresResponse!!.events[i].competitions[0].competitors[0].team.name,
                            teamNameHome = scoresResponse!!.events[i].competitions[0].competitors[1].team.name,
                            pointsAway = scoresResponse!!.events[i].competitions[0].competitors[1].score,
                            pointsHome = scoresResponse!!.events[i].competitions[0].competitors[0].score,
                            datePlayed = scoresResponse!!.events[i].date,
                            statusDesc = scoresResponse!!.events[i].competitions[0].status.type.description
                        ).id(scoresResponse!!.events[i].id).addTo(this)
                    }
                }
            }
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

    // Add scores item
    data class ScoresPreItemEpoxyModel(
        val logoAway: String,
        val logoHome: String,
        val teamNameAway: String,
        val teamNameHome: String,
        val recordAway: String,
        val recordHome: String,
        val dateScheduled: String,
        val gameTime: String,
        val broadcast: String,
        val headline: String,
    ): ViewBindingKotlinModel<ModelScoresPreItemBinding>(R.layout.model_scores_pre_item) {

        override fun ModelScoresPreItemBinding.bind() {
            Picasso.get().load(logoAway).into(awayLogoImageView)
            Picasso.get().load(logoHome).into(homeLogoImageView)

            teamNameAwayTextview.text = teamNameAway
            teamNameHomeTextview.text = teamNameHome

            if (headline.isEmpty()) {
                descriptionGameBottomItemTextview.visibility = View.GONE
            } else {
                descriptionGameBottomItemTextview.visibility = View.VISIBLE
                descriptionGameBottomItemTextview.text = headline
            }

            recordAwayItemTextview.text = recordAway
            recordHomeItemTextview.text = recordHome
            datePreGameItemTextview.text = dateScheduled
            timePreGameItemTextview.text = gameTime
            broadcastPreGameItemTextview.text = broadcast
        }
    }

    // Add scores item
    data class ScoresPostItemEpoxyModel(
        val logoAway: String,
        val logoHome: String,
        val teamNameAway: String,
        val teamNameHome: String,
        val pointsAway: String,
        val pointsHome: String,
        val datePlayed: String,
        val statusDesc: String,
        val headline: String = "",
    ): ViewBindingKotlinModel<ModelScoresPostItemBinding>(R.layout.model_scores_post_item) {

        @SuppressLint("SimpleDateFormat")
        override fun ModelScoresPostItemBinding.bind() {
            Picasso.get().load(logoAway).into(awayLogoImageView)
            Picasso.get().load(logoHome).into(homeLogoImageView)

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

            if (pointsAway.toInt() > pointsHome.toInt()) {
                winnerArrowAwayImageView.visibility = View.VISIBLE
                winnerArrowHomeImageView.visibility = View.INVISIBLE

                pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))
                pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))

                teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))
                teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))

            } else {
                winnerArrowAwayImageView.visibility = View.INVISIBLE
                winnerArrowHomeImageView.visibility = View.VISIBLE

                pointsAwayItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))
                pointsHomeItemTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))

                teamNameAwayTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.grey))
                teamNameHomeTextview.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,R.color.white))

            }

            statusGameItemTextview.text = statusDesc

            // Parse ISO format to "E, M/d"
            val actual = OffsetDateTime.parse(datePlayed, DateTimeFormatter.ISO_DATE_TIME)
            val formatter = DateTimeFormatter.ofPattern("E',' M/d")
            val formatDateTime = actual.format(formatter)
            datePostGameItemTextview.text = formatDateTime.toString()

        }
    }












    //        // Add scores item
//        data class ScoresItemEpoxyModel(
//            val logoAway: String,
//            val logoHome: String,
//            val teamNameAway: String,
//            val teamNameHome: String,
//            val pointsAway: String,
//            val pointsHome: String,
//            val recordAway: String,
//            val recordHome: String,
//            val dateScheduled: String,
//            val gameTime: String,
//            val broadcast: String,
//            val datePlayed: String,
//            val headline: String,
//            val status: String,
//        ): ViewBindingKotlinModel<ModelScoresItemBinding>(R.layout.model_scores_item) {
//
//            override fun ModelScoresItemBinding.bind() {
//
//                Picasso.get().load(logoAway).into(awayLogoImageView)
//                Picasso.get().load(logoHome).into(homeLogoImageView)
//
//                teamNameAwayTextview.text = teamNameAway
//                teamNameHomeTextview.text = teamNameHome
//
//                if (headline.isEmpty()) {
//                    descriptionGameBottomItemTextview.visibility = View.GONE
//                } else {
//                    descriptionGameBottomItemTextview.visibility = View.VISIBLE
//                    descriptionGameBottomItemTextview.text = headline
//                }
//
//
//                when (status) {
//                    "pre" -> {
//                        // Visible TextViews
//                        recordAwayItemTextview.visibility = View.VISIBLE
//                        recordHomeItemTextview.visibility = View.VISIBLE
//                        datePreGameItemTextview.visibility = View.VISIBLE
//                        timePreGameItemTextview.visibility = View.VISIBLE
//                        broadcastPreGameItemTextview.visibility = View.VISIBLE
//                        // Invisible TextViews
//                        statusGameItemTextview.visibility = View.INVISIBLE
//                        datePostGameItemTextview.visibility = View.INVISIBLE
//                        pointsAwayItemTextview.visibility = View.INVISIBLE
//                        pointsHomeItemTextview.visibility = View.INVISIBLE
//                        winnerArrowAwayImageView.visibility = View.INVISIBLE
//                        winnerArrowHomeImageView.visibility = View.INVISIBLE
//
//                        recordAwayItemTextview.text = recordAway
//                        recordHomeItemTextview.text = recordHome
//                        datePreGameItemTextview.text = dateScheduled
//                        timePreGameItemTextview.text = gameTime
//                        broadcastPreGameItemTextview.text = broadcast
//                    }
//                    "post" -> {
//                        // Visible TextViews
//                        statusGameItemTextview.visibility = View.VISIBLE
//                        datePostGameItemTextview.visibility = View.VISIBLE
//                        pointsAwayItemTextview.visibility = View.VISIBLE
//                        pointsHomeItemTextview.visibility = View.VISIBLE
//                        winnerArrowAwayImageView.visibility = View.VISIBLE
//                        winnerArrowHomeImageView.visibility = View.VISIBLE
//
//                        // Invisible TextViews
//                        recordAwayItemTextview.visibility = View.INVISIBLE
//                        recordHomeItemTextview.visibility = View.INVISIBLE
//                        datePreGameItemTextview.visibility = View.INVISIBLE
//                        timePreGameItemTextview.visibility = View.INVISIBLE
//                        broadcastPreGameItemTextview.visibility = View.INVISIBLE
//
//
//                        pointsAwayItemTextview.text = pointsAway
//                        pointsHomeItemTextview.text = pointsHome
//
//                        if (pointsAway > pointsHome) {
//                            winnerArrowAwayImageView.visibility = View.VISIBLE
//                            winnerArrowHomeImageView.visibility = View.INVISIBLE
//                        } else {
//                            winnerArrowAwayImageView.visibility = View.INVISIBLE
//                            winnerArrowHomeImageView.visibility = View.VISIBLE
//                        }
//
//                        // convert date format TODO
//                        datePostGameItemTextview.text = ""
//                    }
//                }
//
//            }
//        }
}
