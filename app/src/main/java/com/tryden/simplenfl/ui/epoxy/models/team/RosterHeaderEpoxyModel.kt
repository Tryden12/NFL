package com.tryden.simplenfl.ui.epoxy.models.team

import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelRosterHeaderBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.ui.models.RosterViewState
import com.tryden.simplenfl.ui.updateLabelColor

// Roster type header
data class RosterHeaderEpoxyModel(
    val rosterType: String,
    val onSelectedSort: (RosterViewState.Sort) -> Unit
): ViewBindingKotlinModel<ModelRosterHeaderBinding>(R.layout.model_roster_header) {

    override fun ModelRosterHeaderBinding.bind() {
        seasonTypeTextview.text = rosterType

        labelNameTextView.setOnClickListener {
            onSelectedSort(RosterViewState.Sort.NAME)
            updateLabelColor(RosterViewState.Sort.NAME)
        }

        labelPositionTextView.setOnClickListener {
            onSelectedSort(RosterViewState.Sort.POSITION)
            updateLabelColor(RosterViewState.Sort.POSITION)

        }
        labelAgeTextView.setOnClickListener {
            onSelectedSort(RosterViewState.Sort.AGE)
            updateLabelColor(RosterViewState.Sort.AGE)

        }
        labelHeightTextView.setOnClickListener {
            onSelectedSort(RosterViewState.Sort.HEIGHT)
            updateLabelColor(RosterViewState.Sort.HEIGHT)

        }
    }
}