package com.tryden.simplenfl.epoxy.models.teams

import android.view.View
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding

data class TitleTeamsListEpoxyModel(
    val useLogo: Boolean,
    val title: String
): ViewBindingKotlinModel<ModelSectionHeaderBinding>
    (R.layout.model_section_header) {

    override fun ModelSectionHeaderBinding.bind() {
        if (!useLogo) {
            logoSectionImageView.visibility = View.GONE
        } else {
            logoSectionImageView.setImageResource(R.drawable.placeholder_logo)
        }
        titleSectionTextView.text = title
    }
}