package com.tryden.simplenfl.network.response.models.roster

data class Position(
    val abbreviation: String = "",
    val displayName: String = "",
    val id: String = "",
    val leaf: Boolean = false,
    val name: String = "",
    val parent: com.tryden.simplenfl.network.response.models.roster.Parent = com.tryden.simplenfl.network.response.models.roster.Parent()
)