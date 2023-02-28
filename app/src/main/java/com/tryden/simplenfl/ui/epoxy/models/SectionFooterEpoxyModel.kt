package com.tryden.simplenfl.ui.epoxy.models

import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionFooterBinding


data class SectionFooterEpoxyModel(
    val useSection: Boolean = true
): ViewBindingKotlinModel<ModelSectionFooterBinding>
    (R.layout.model_section_footer) {

    override fun ModelSectionFooterBinding.bind() {
        // nothing to do
    }
}