package com.tryden.simplenfl.network.response.teams.models.article

data class LinksRelated(
    val api: ApiRelated = ApiRelated(),
    val mobile: MobileRelated = MobileRelated(),
    val web: WebRelated = WebRelated()
)