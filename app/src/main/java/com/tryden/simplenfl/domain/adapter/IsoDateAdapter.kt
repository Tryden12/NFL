package com.tryden.simplenfl.domain.adapter

import com.tryden.simplenfl.application.SimpleNFLApplication
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class IsoDateAdapter  {

    fun formatDate(isoDate: String): String {
        // Parse ISO format to "Sun, 10/18"
        val responseDate = OffsetDateTime
            .parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter = DateTimeFormatter.ofPattern("E',' M/d")

        return responseDate.format(formatter)
    }

    fun formatGameTime(isoDate: String): String {
        // Parse ISO format to "4:30 PM"
        val responseDate = OffsetDateTime
            .parse(isoDate, DateTimeFormatter.ISO_DATE_TIME)
            .atZoneSameInstant(
                ZoneId.of(SimpleNFLApplication.zoneId))
        val formatter = DateTimeFormatter.ofPattern("h:mm a")

        return responseDate.format(formatter)
    }

}