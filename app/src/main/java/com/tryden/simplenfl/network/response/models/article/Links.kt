package com.tryden.simplenfl.network.response.models.article

data class Links(
    val api: com.tryden.simplenfl.network.response.models.article.Api = com.tryden.simplenfl.network.response.models.article.Api(),
    val app: com.tryden.simplenfl.network.response.models.article.App = com.tryden.simplenfl.network.response.models.article.App(),
    val mobile: com.tryden.simplenfl.network.response.models.article.Mobile = com.tryden.simplenfl.network.response.models.article.Mobile(),
    val web: com.tryden.simplenfl.network.response.models.article.Web = com.tryden.simplenfl.network.response.models.article.Web()
)