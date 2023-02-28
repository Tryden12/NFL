package com.tryden.simplenfl.ui.epoxy.models

import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionHeaderCenteredBinding

data class SectionHeaderCenteredEpoxyModel(
    val sectionHeader: String
): ViewBindingKotlinModel<ModelSectionHeaderCenteredBinding>
    (R.layout.model_section_header_centered) {

    override fun ModelSectionHeaderCenteredBinding.bind() {

        // Section header
       headerCenteredTextview.text = sectionHeader

    }
}