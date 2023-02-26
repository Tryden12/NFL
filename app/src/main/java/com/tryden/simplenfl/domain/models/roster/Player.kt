package com.tryden.simplenfl.domain.models.roster

data class Player(
    val id: String = "",
    val displayName: String = "",
    val shortName: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val displayHeight: String = "",
    val displayWeight: String = "",
    val age: String = "",
    val experience: Int = 0,
    val headshot: String = "",
    val jersey: String = "",
    val position: String = "",
    val group: String = ""
)