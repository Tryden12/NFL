package com.tryden.simplenfl.network.response.teams.models.scores

data class GeoBroadcast(
    val lang: String = "",
    val market: Market = Market(),
    val media: Media = Media(),
    val region: String = "",
    val type: Type = Type()
)