package com.tryden.simplenfl.ui.epoxy.models.team

import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSpacerRosterItemBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

data class SpacerRosterItemEpoxyItem(
    val useSpacer:Boolean = true
):ViewBindingKotlinModel<ModelSpacerRosterItemBinding>(R.layout.model_spacer_roster_item) {
    override fun ModelSpacerRosterItemBinding.bind() {
        // nothing to do
    }
}