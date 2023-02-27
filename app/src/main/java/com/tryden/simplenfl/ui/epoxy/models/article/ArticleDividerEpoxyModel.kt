package com.tryden.simplenfl.ui.epoxy.models.article

import android.view.View
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelDividerSolidGreyBinding
import com.tryden.simplenfl.ui.epoxy.ViewBindingKotlinModel

data class ArticleDividerEpoxyModel(
    val divider: Boolean = true
): ViewBindingKotlinModel<ModelDividerSolidGreyBinding>
    (R.layout.model_divider_solid_grey) {

    override fun ModelDividerSolidGreyBinding.bind() {
        if (!divider) {
            dividerView.visibility = View.GONE
        }
    }
}