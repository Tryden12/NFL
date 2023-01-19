package com.tryden.simplenfl.network.response.teams.models.article

data class LinksTeam(
    val api: ApiTeam = ApiTeam(),
    val mobile: MobileTeam = MobileTeam(),
    val web: WebTeam = WebTeam()
)