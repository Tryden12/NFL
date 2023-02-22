package com.tryden.simplenfl.network.response.models.player

data class Position(
    val `$ref`: String = "",
    val abbreviation: String = "",
    val displayName: String = "",
    val id: String = "",
    val leaf: Boolean = false,
    val name: String = "",
    val parent: com.tryden.simplenfl.network.response.models.player.Parent = com.tryden.simplenfl.network.response.models.player.Parent()
)