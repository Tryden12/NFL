package com.tryden.simplenfl.network.response.teams.models.scores

data class Weather(
    val conditionId: String = "",
    val displayValue: String = "",
    val highTemperature: Int = 0,
//    val link: LinkXXXXX = LinkXXXXX(),
    val temperature: Int = 0
)