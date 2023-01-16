package com.tryden.simplenfl.teams.models.team

data class Broadcast(
    val lang: String = "",
    val market: Market = Market(),
    val media: Media = Media(),
    val region: String = "",
    val type: Type = Type()
)