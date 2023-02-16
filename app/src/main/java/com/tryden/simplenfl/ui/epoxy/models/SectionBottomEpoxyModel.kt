package com.tryden.simplenfl.ui.epoxy.models

import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionBottomBinding


data class SectionBottomEpoxyModel(
    val useSection: Boolean
): ViewBindingKotlinModel<ModelSectionBottomBinding>
    (R.layout.model_section_bottom) {

    override fun ModelSectionBottomBinding.bind() {
        // nothing to do
    }
}