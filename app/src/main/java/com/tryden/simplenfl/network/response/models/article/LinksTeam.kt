package com.tryden.simplenfl.network.response.models.article

data class LinksTeam(
    val api: com.tryden.simplenfl.network.response.models.article.ApiTeam = com.tryden.simplenfl.network.response.models.article.ApiTeam(),
    val mobile: com.tryden.simplenfl.network.response.models.article.MobileTeam = com.tryden.simplenfl.network.response.models.article.MobileTeam(),
    val web: com.tryden.simplenfl.network.response.models.article.WebTeam = com.tryden.simplenfl.network.response.models.article.WebTeam()
)