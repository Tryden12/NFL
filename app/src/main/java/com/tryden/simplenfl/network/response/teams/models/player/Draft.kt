package com.tryden.simplenfl.network.response.teams.models.player

data class Draft(
    val displayText: String = "",
    val pick: Pick = Pick(),
    val round: Int = 0,
    val selection: Int = 0,
    val team: TeamDraft = TeamDraft(),
    val year: Int = 0
)