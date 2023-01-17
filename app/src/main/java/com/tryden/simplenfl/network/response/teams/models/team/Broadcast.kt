package com.tryden.simplenfl.network.response.teams.models.team

data class Broadcast(
    val lang: String = "",
    val market: com.tryden.simplenfl.network.response.teams.models.team.Market = com.tryden.simplenfl.network.response.teams.models.team.Market(),
    val media: com.tryden.simplenfl.network.response.teams.models.team.Media = com.tryden.simplenfl.network.response.teams.models.team.Media(),
    val region: String = "",
    val type: com.tryden.simplenfl.network.response.teams.models.team.Type = com.tryden.simplenfl.network.response.teams.models.team.Type()
)