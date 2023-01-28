package com.tryden.simplenfl.epoxy.controllers.models

import androidx.constraintlayout.widget.ConstraintSet
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelGapBlack16dpBottomBinding

data class GapBlack16BottomEpoxyModel(
    val gapHeight: Int
): ViewBindingKotlinModel<ModelGapBlack16dpBottomBinding>(R.layout.model_gap_black_16dp_bottom) {

    override fun ModelGapBlack16dpBottomBinding.bind() {
        val set = ConstraintSet()
        set.constrainDefaultHeight(gapConstraintLayout.id, gapHeight)
    }
}