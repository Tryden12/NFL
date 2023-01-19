package com.tryden.simplenfl.teams

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelScoresItemBinding
import com.tryden.simplenfl.databinding.ModelScoresSeasonTypeHeaderBinding
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding
import com.tryden.simplenfl.databinding.ModelTeamPageHeaderBinding
import com.tryden.simplenfl.network.response.teams.models.team.TeamObject

class TeamPageEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var teamResponse: TeamObject? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if (isLoading) {
            // show loading state
            return
        }

        // Add team page header model
        data class HeaderEpoxyModel(
            val logoUrl: String,
            val teamName: String,
            val record: String
        ): ViewBindingKotlinModel<ModelTeamPageHeaderBinding>(R.layout.model_team_page_header) {

            override fun ModelTeamPageHeaderBinding.bind() {
                teamNameTextView.text = teamName
                recordTextView.text = "($record)"

                Picasso.get().load(logoUrl).into(logoImageView)
            }
        }

        // Add section header item model
        data class SectionHeaderEpoxyModel(
            val title: String
        ): ViewBindingKotlinModel<ModelSectionHeaderBinding>(R.layout.model_section_header) {

            override fun ModelSectionHeaderBinding.bind() {
                titleSectionTextView.text = title
            }
        }

        // Add scores season type header
        data class ScoresSeasonTypeEpoxyModel(
            val seasonType: String
        ): ViewBindingKotlinModel<ModelScoresSeasonTypeHeaderBinding>(R.layout.model_scores_season_type_header) {

            override fun ModelScoresSeasonTypeHeaderBinding.bind() {

                seasonTypeTextview.text = seasonType
            }
        }

        // Add scores item
        data class ScoresItemEpoxyModel(
            val logoAway: String,
            val logoHome: String,
            val teamNameAway: String,
            val teamNameHome: String,
            val pointsAway: String,
            val pointsHome: String,
            val datePlayed: String
        ): ViewBindingKotlinModel<ModelScoresItemBinding>(R.layout.model_scores_item) {

            override fun ModelScoresItemBinding.bind() {

                Picasso.get().load(logoAway).into(awayLogoImageView)
                Picasso.get().load(logoHome).into(homeLogoImageView)

                teamNameAwayTextview.text = teamNameAway
                teamNameHomeTextview.text = teamNameHome

                pointsAwayItemTextview.text = pointsAway
                pointsHomeItemTextview.text = pointsHome

                if (pointsAway > pointsHome) {
                    winnerArrowAwayImageView.visibility = View.VISIBLE
                    winnerArrowHomeImageView.visibility = View.INVISIBLE
                } else {
                    winnerArrowAwayImageView.visibility = View.INVISIBLE
                    winnerArrowHomeImageView.visibility = View.VISIBLE
                }

                // convert date format TODO
                dateGameItemTextview.text = ""
            }
        }
    }
}