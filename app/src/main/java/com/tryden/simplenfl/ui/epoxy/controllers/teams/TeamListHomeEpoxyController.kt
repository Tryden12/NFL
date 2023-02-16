package com.tryden.simplenfl.ui.epoxy.controllers.teams

import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.teams.TeamsListVerticalEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.teams.TitleTeamsListEpoxyModel
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


    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }
        if (teamsListResponse == null) {
            // todo error state
            return
        }

        TitleTeamsListEpoxyModel(
            useLogo = true,
            title = "Choose Team"
        ).id("title-teams").spanSizeOverride { _, _, _ -> 4 }.addTo(this)


        for (i in teamsListResponse!!.sports[0].leagues[0].teams.indices) {

            TeamsListVerticalEpoxyModel(
                teamId = teamsListResponse!!.sports[0].leagues[0].teams[i].team.id.toInt(),
                logoUrl = teamsListResponse!!.sports[0].leagues[0].teams[i].team.logos[0].href,
                teamName = teamsListResponse!!.sports[0].leagues[0].teams[i].team.shortDisplayName,
                onTeamSelected = onTeamSelected
            ).id(teamsListResponse!!.sports[0].leagues[0].teams[i].team.id).addTo(this)

        }
    }

}