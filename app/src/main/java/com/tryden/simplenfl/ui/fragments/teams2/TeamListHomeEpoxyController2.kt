package com.tryden.simplenfl.ui.fragments.teams2

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.epoxy.controllers.teams.TeamListHomeEpoxyController

class TeamListHomeEpoxyController2(
    private val onTeamSelected: (Int) -> Unit
): TypedEpoxyController<List<UiTeam>>() {

    override fun buildModels(data: List<UiTeam>) {
        if (data.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        TeamListHomeEpoxyController.TitleTeamsListEpoxyModel(
            useLogo = true,
            title = "Choose Team"
        ).id("title-teams").spanSizeOverride { _, _, _ -> 4 }.addTo(this)

        data.forEach { uiTeam ->
            TeamListHomeEpoxyController.TeamVerticalListEpoxyModel(
                teamId = uiTeam.id,
                logoUrl = uiTeam.imageUrl,
                teamName = uiTeam.name,
                onTeamSelected = onTeamSelected
            ).id("team_${uiTeam.id}").addTo(this)
        }
    }
}