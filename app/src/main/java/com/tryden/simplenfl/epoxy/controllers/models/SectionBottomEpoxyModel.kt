package com.tryden.simplenfl.epoxy.controllers.models

import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionBottomBinding
import com.tryden.simplenfl.databinding.ModelSectionHeaderCenteredBinding


data class SectionBottomEpoxyModel(
    val useSection: Boolean
): ViewBindingKotlinModel<ModelSectionBottomBinding>
    (R.layout.model_section_bottom) {

    override fun ModelSectionBottomBinding.bind() {
        // nothing to do
    }
}