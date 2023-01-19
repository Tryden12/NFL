package com.tryden.simplenfl.network.response.teams.models.article

data class Links(
    val api: Api = Api(),
    val app: App = App(),
    val mobile: Mobile = Mobile(),
    val web: Web = Web()
)