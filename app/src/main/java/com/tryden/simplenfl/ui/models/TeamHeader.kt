package com.tryden.simplenfl.ui.models

import com.tryden.simplenfl.domain.models.team.Team

data class TeamHeader(
    val team: Team,
    val isFavorite: Boolean
)