package com.tryden.simplenfl.network.response.teams.models.roster

data class Position(
    val abbreviation: String = "",
    val displayName: String = "",
    val id: String = "",
    val leaf: Boolean = false,
    val name: String = "",
    val parent: Parent = Parent()
)