package com.tryden.simplenfl.epoxy.controllers.team.header

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelScoresSeasonTypeHeaderBinding
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding
import com.tryden.simplenfl.databinding.ModelTeamPageHeaderBinding
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.LoadingNoCircleEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.team.TeamObjectResponse

class TeamPageHeaderEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var teamResponse: TeamObjectResponse? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if (isLoading) {
            LoadingNoCircleEpoxyModel().id("loading").addTo(this)
            return
        }

        if (teamResponse == null) {
            // todo error state
            return
        }

        HeaderEpoxyModel(
            logoUrl = teamResponse!!.team.logos[1].href,
            teamName = teamResponse!!.team.displayName,
            record = teamResponse!!.team.record.items[0].summary
        ).id("team-header").addTo(this)

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

            if (logoUrl.isEmpty()) {
                Picasso.get()
                    .load(R.drawable.placeholder_logo)
                    .placeholder(R.drawable.placeholder_logo)
                    .error(R.drawable.placeholder_logo)
                    .into(logoImageView)
            } else {
                Picasso.get().load(logoUrl).into(logoImageView)
            }
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
}