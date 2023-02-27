package com.tryden.simplenfl.ui.epoxy.models.team

import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelRosterHeaderBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

// Roster type header
data class RosterHeaderEpoxyModel(
    val rosterType: String
): ViewBindingKotlinModel<ModelRosterHeaderBinding>(R.layout.model_roster_header) {

    override fun ModelRosterHeaderBinding.bind() {
        seasonTypeTextview.text = rosterType

        labelPositionTextView.setOnClickListener {
            // todo
        }
        labelAgeTextView.setOnClickListener {
            // todo
        }
        labelHeightTextView.setOnClickListener {
            // todo
        }
    }
}