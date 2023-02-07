package com.tryden.simplenfl.epoxy.models

import android.view.View
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelScoresScheduledWithHeaderItemBinding
import com.tryden.simplenfl.databinding.ModelSectionHeaderCenteredBinding
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

data class SectionHeaderCenteredEpoxyModel(
    val sectionHeader: String
): ViewBindingKotlinModel<ModelSectionHeaderCenteredBinding>
    (R.layout.model_section_header_centered) {

    override fun ModelSectionHeaderCenteredBinding.bind() {

        // Section header
       headerCenteredTextview.text = sectionHeader

    }
}