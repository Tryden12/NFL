package com.tryden.simplenfl.network.response.teams.models.news

data class Links(
    val api: Api = Api(),
    val mobile: Mobile = Mobile(),
    val web: Web = Web()
)