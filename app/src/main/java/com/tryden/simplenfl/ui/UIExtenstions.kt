package com.tryden.simplenfl

import androidx.core.content.ContextCompat
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelRosterHeaderBinding
import com.tryden.simplenfl.ui.models.RosterViewState
import com.tryden.simplenfl.ui.models.RosterViewState.Sort.*

fun ModelRosterHeaderBinding.updateLabelColor(sortBy: RosterViewState.Sort) {
    when (sortBy) {
        NAME -> {
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.blue_link))
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
        }
        POSITION -> {
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.blue_link))
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
        }
        AGE -> {
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.blue_link))
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
        }
        HEIGHT -> {
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.blue_link))
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context, R.color.white))

        }
    }
}