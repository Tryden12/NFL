package com.tryden.simplenfl.network.response.models.player

data class Draft(
    val displayText: String = "",
    val pick: com.tryden.simplenfl.network.response.models.player.Pick = com.tryden.simplenfl.network.response.models.player.Pick(),
    val round: Int = 0,
    val selection: Int = 0,
    val team: com.tryden.simplenfl.network.response.models.player.TeamDraft = com.tryden.simplenfl.network.response.models.player.TeamDraft(),
    val year: Int = 0
)