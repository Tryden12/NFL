package com.tryden.simplenfl.network.response.teams.models.scoreboardOld

data class GeoBroadcast(
    val lang: String = "",
    val market: MarketGeoBroadcast = MarketGeoBroadcast(),
    val media: MediaGeoBroadcast = MediaGeoBroadcast(),
    val region: String = "",
    val type: TypeGeoBroadcast = TypeGeoBroadcast()
)