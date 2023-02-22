package com.tryden.simplenfl.network.response.models.news

data class LinksAthlete(
    val api: com.tryden.simplenfl.network.response.models.news.ApiAthlete = com.tryden.simplenfl.network.response.models.news.ApiAthlete(),
    val mobile: com.tryden.simplenfl.network.response.models.news.MobileAthlete = com.tryden.simplenfl.network.response.models.news.MobileAthlete(),
    val web: com.tryden.simplenfl.network.response.models.news.WebAthlete = com.tryden.simplenfl.network.response.models.news.WebAthlete()
)