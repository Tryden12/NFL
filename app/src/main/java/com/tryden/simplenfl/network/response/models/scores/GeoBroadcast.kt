package com.tryden.simplenfl.network.response.models.scores

data class GeoBroadcast(
    val lang: String = "",
    val market: com.tryden.simplenfl.network.response.models.scores.Market = com.tryden.simplenfl.network.response.models.scores.Market(),
    val media: com.tryden.simplenfl.network.response.models.scores.Media = com.tryden.simplenfl.network.response.models.scores.Media(),
    val region: String = "",
    val type: com.tryden.simplenfl.network.response.models.scores.Type = com.tryden.simplenfl.network.response.models.scores.Type()
)