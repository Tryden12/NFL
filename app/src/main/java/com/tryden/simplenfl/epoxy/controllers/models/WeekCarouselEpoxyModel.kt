package com.tryden.simplenfl.epoxy.controllers.models

import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelScoresCarouselDateItemBinding

data class WeekCarouselEpoxyModel(
    val week: Week
): ViewBindingKotlinModel<ModelScoresCarouselDateItemBinding>
    (R.layout.model_scores_carousel_date_item){

    override fun ModelScoresCarouselDateItemBinding.bind() {
        labelTextView.text = week.label
        datesTextView.text = week.dates

        root.setOnClickListener {
            week.onWeekSelected(week.range)
        }
    }
}

data class Week(
    val label: String,
    val dates: String,
    val number: String,
    val range: String,
    val onWeekSelected: (String) -> Unit
)