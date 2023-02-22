package com.tryden.simplenfl.network.response.models.news

data class Links(
    val api: com.tryden.simplenfl.network.response.models.news.Api = com.tryden.simplenfl.network.response.models.news.Api(),
    val mobile: com.tryden.simplenfl.network.response.models.news.Mobile = com.tryden.simplenfl.network.response.models.news.Mobile(),
    val web: com.tryden.simplenfl.network.response.models.news.Web = com.tryden.simplenfl.network.response.models.news.Web()
)