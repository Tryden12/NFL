package com.tryden.simplenfl.network.response.teams.models.news

data class LinksAthlete(
    val api: ApiAthlete = ApiAthlete(),
    val mobile: MobileAthlete = MobileAthlete(),
    val web: WebAthlete = WebAthlete()
)