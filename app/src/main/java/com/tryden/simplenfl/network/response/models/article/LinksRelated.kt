package com.tryden.simplenfl.network.response.models.article

data class LinksRelated(
    val api: com.tryden.simplenfl.network.response.models.article.ApiRelated = com.tryden.simplenfl.network.response.models.article.ApiRelated(),
    val mobile: com.tryden.simplenfl.network.response.models.article.MobileRelated = com.tryden.simplenfl.network.response.models.article.MobileRelated(),
    val web: com.tryden.simplenfl.network.response.models.article.WebRelated = com.tryden.simplenfl.network.response.models.article.WebRelated()
)