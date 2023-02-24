package com.tryden.simplenfl.ui.epoxy.controllers.teams

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.teams.TeamsListVerticalEpoxyModel
import com.tryden.simplenfl.ui.epoxy.models.teams.TitleTeamsListEpoxyModel
import com.tryden.simplenfl.domain.models.teamslist.UiTeam

class TeamListHomeEpoxyController2(
    private val onTeamSelected: (String) -> Unit
): TypedEpoxyController<List<UiTeam>>() {

    override fun buildModels(data: List<UiTeam>) {
        if (data.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        TitleTeamsListEpoxyModel(
            useLogo = true,
            title = "Choose Team"
        ).id("title-teams").spanSizeOverride { _, _, _ -> 4 }.addTo(this)

        data.forEach { uiTeam ->
            TeamsListVerticalEpoxyModel(
                teamId = uiTeam.id,
                logoUrl = uiTeam.imageUrl,
                teamName = uiTeam.name,
                onTeamSelected = onTeamSelected
            ).id("team_${uiTeam.id}").addTo(this)
        }
    }
}