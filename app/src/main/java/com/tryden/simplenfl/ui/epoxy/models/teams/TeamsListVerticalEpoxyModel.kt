package com.tryden.simplenfl.ui.epoxy.models.teams

import com.squareup.picasso.Picasso
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelTeamsListVerticalItemBinding

data class TeamsListVerticalEpoxyModel(
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