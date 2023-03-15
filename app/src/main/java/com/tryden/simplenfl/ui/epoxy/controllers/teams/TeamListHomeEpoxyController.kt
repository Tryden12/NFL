package com.tryden.simplenfl.ui.epoxy.controllers.teams

import com.airbnb.epoxy.TypedEpoxyController
import com.tryden.simplenfl.ui.epoxy.models.teams.TeamsListVerticalEpoxyModel
import com.tryden.simplenfl.domain.models.teamslist.UiTeam
import com.tryden.simplenfl.ui.epoxy.models.SectionHeaderEpoxyModel

class TeamListHomeEpoxyController(
    private val onTeamSelected: (String) -> Unit
): TypedEpoxyController<List<UiTeam>>() {

    override fun buildModels(data: List<UiTeam>) {
        if (data.isEmpty()) {
//            addLoadingModel()
            /** add shimmer loading model(s) **/
            SectionHeaderEpoxyModel(null, "", true).id("shimmer-header")
                .spanSizeOverride{ _, _, _ -> 4 }.addTo(this)
            return
        }

        SectionHeaderEpoxyModel(
            title = "Choose Team",
            logo = "",
            logoVisible = true
        ).id("title-teams").spanSizeOverride{ _, _, _ -> 4 }.addTo(this)

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