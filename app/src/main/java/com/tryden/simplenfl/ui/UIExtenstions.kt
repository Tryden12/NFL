package com.tryden.simplenfl.ui

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelRosterHeaderBinding
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.models.RosterViewState
import com.tryden.simplenfl.ui.models.RosterViewState.Sort.*
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


/**
 * Method for adding the a loading epoxy model to the controller.
 */
fun EpoxyController.addLoadingModel() {
    LoadingEpoxyModel().id("loading").addTo(this)
}

/**
 * Function for returning an OffsetDateTime to be used for sorting.
 */
fun formatPublishedForSorting(isoDate: String): String{
    return OffsetDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME).toString()
}

/**
 * Function for calculating the time between current time and the parameter date.
 * Returns a formatted String similar to the examples below:
 * '21d' = 21 days ago
 * '5h' = 5 days ago
 */
fun formatPublishedTime(date: String): String{
    val actual = OffsetDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)

    val now: Instant = Instant.now()
    val hours: Long = ChronoUnit.HOURS.between(actual.toInstant(), now)
    val days: Long = ChronoUnit.DAYS.between(actual.toInstant(), now)

    return if (hours > 24) {
        days.toString() + "d"
    } else {
        hours.toString() + "h"
    }
}


/**
 * Method for updating roster label colors onClick whenever a label is chosen to sort:
 * Name, Position, Age, Height
 */
fun ModelRosterHeaderBinding.updateLabelColor(sortBy: RosterViewState.Sort) {
    when (sortBy) {
        NAME -> {
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.blue_link))
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
        }
        POSITION -> {
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.blue_link))
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
        }
        AGE -> {
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.blue_link))
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
        }
        HEIGHT -> {
            labelHeightTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.blue_link))
            labelNameTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelPositionTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))
            labelAgeTextView.setTextColor(ContextCompat.getColor(SimpleNFLApplication.context,
                R.color.white))

        }
    }
}