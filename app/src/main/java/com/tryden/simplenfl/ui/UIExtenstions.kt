package com.tryden.simplenfl

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyController
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelRosterHeaderBinding
import com.tryden.simplenfl.ui.epoxy.models.scores.LoadingEpoxyModel
import com.tryden.simplenfl.ui.models.RosterViewState
import com.tryden.simplenfl.ui.models.RosterViewState.Sort.*
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


fun EpoxyController.addLoadingModel() {
    LoadingEpoxyModel().id("loading").addTo(this)
}

fun formatPublishedTime(date: String): String{
    // Format date to "4:30 PM"
    val actual = OffsetDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)

    val now: Instant = Instant.now()
    val hours: Long = ChronoUnit.HOURS.between(actual.toInstant(), now)
    val days: Long = ChronoUnit.DAYS.between(actual.toInstant(), now)

    return if (hours > 24) {
        days.toString() + "d"
    } else {
        hours.toString() + "h"
    }

//    val formatter = DateTimeFormatter.ofPattern("h:mm a")
//    return actual.format(formatter)



}

//fun formatPublished(date: String): String {
//
//
//
//
//    return ""
//}

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