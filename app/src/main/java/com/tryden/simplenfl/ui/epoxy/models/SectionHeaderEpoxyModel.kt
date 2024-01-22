package com.tryden.simplenfl.ui.epoxy.models

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelSectionHeaderBinding

/**
 * Section Header
 */
data class SectionHeaderEpoxyModel(
    val title: String?,
    val logo: String,
    val logoVisible: Boolean
): ViewBindingKotlinModel<ModelSectionHeaderBinding>
    (R.layout.model_section_header) {

    override fun ModelSectionHeaderBinding.bind() {

        shimmerLayout.isVisible = title == null
        constraintLayout.isInvisible = title == null

        title?.let {
            shimmerLayout.stopShimmer()
            // Set title text
            titleSectionTextView.text = title

            if (logo.isEmpty() && logoVisible) {
                /** Use NFL logo **/
                logoSectionImageView.visibility = View.VISIBLE
                logoSectionImageView.setImageResource(R.drawable.placeholder_logo)
            } else if (logo.isNotEmpty() && logoVisible) {
                /** Use Team logo **/
                logoSectionImageView.visibility = View.VISIBLE
                Picasso.get().load(logo).into(logoSectionImageView)
            } else {
                /** No Logo Should Be Visible **/
                logoSectionImageView.visibility = View.GONE
            }
        } ?: shimmerLayout.startShimmer()
    }
}