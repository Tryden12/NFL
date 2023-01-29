package com.tryden.simplenfl.epoxy.controllers.models

import android.view.View
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding

// Section header
data class SectionHeaderEpoxyModel(
    val title: String,
    val logoVisible: Boolean,
    val usePlaceholderLogo: Boolean,
    val logoUrl: String
): ViewBindingKotlinModel<ModelSectionHeaderBinding>
    (R.layout.model_section_header) {

    override fun ModelSectionHeaderBinding.bind() {

        if (logoVisible && usePlaceholderLogo) {
            /** Use NFL logo **/
            logoSectionImageView.visibility = View.VISIBLE
            logoSectionImageView.setBackgroundResource(R.drawable.placeholder_logo)
        } else if (logoVisible && !usePlaceholderLogo) {
            /** Use Team logo **/
            if (logoUrl.isNotEmpty()) {
                logoSectionImageView.visibility = View.VISIBLE
                Picasso.get().load(logoUrl).into(logoSectionImageView)
            } else {
                /** Use NFL logo **/
                logoSectionImageView.setBackgroundResource(R.drawable.placeholder_logo)
            }
        }

        // Set title text
        titleSectionTextView.text = title

    }
}