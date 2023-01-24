package com.tryden.simplenfl.teams

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding
import com.tryden.simplenfl.databinding.ModelTeamsListVerticalItemBinding
import com.tryden.simplenfl.network.response.teams.models.teams.AllTeamsResponse

class TeamListHomeEpoxyController(
    private val onTeamSelected: (Int) -> Unit
): EpoxyController() {


    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var teamsListResponse: AllTeamsResponse? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }

//    val teamsList = teamsListResponse!!.sports[0].leagues[0].teams

    override fun buildModels() {
        if (isLoading) {
//            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }
        if (teamsListResponse == null) {
            // todo error state
            return
        }

        TitleTeamsListEpoxyModel(
            title = "Select Team"
//        ).id("title-teams").addTo(this)
        ).id("title-teams").spanSizeOverride { _, _, _ -> 4 }.addTo(this)


        for (i in teamsListResponse!!.sports[0].leagues[0].teams.indices) {

            TeamVerticalListEpoxyModel(
                teamId = teamsListResponse!!.sports[0].leagues[0].teams[i].team.id.toInt(),
                logoUrl = teamsListResponse!!.sports[0].leagues[0].teams[i].team.logos[0].href,
                teamName = teamsListResponse!!.sports[0].leagues[0].teams[i].team.shortDisplayName,
                onTeamSelected = onTeamSelected
            ).id(teamsListResponse!!.sports[0].leagues[0].teams[i].team.id).addTo(this)

        }
    }

    // Add list header
    data class TitleTeamsListEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelSectionHeaderBinding>
        (R.layout.model_section_header) {

        override fun ModelSectionHeaderBinding.bind() {
            titleSectionTextView.text = title
        }
    }

    // Add teams
    data class TeamVerticalListEpoxyModel(
        val teamId: Int,
        val logoUrl: String,
        val teamName: String,
        val onTeamSelected: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelTeamsListVerticalItemBinding>
        (R.layout.model_teams_list_vertical_item) {

        override fun ModelTeamsListVerticalItemBinding.bind() {

            if (logoUrl.isEmpty()) {
                Picasso.get()
                    .load(R.drawable.placeholder_logo)
                    .placeholder(R.drawable.placeholder_logo)
                    .error(R.drawable.placeholder_logo)
                    .into(logoImageView)
            } else {
                Picasso.get().load(logoUrl).into(logoImageView)
            }
            teamNameTextView.text = teamName



            // Click listener for teams pages
            root.setOnClickListener {
                onTeamSelected(teamId)
            }
        }
    }
}