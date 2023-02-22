package com.tryden.simplenfl.network.response.models.roster

data class Item(
    val age: Int = 0,
    val alternateIds: com.tryden.simplenfl.network.response.models.roster.AlternateIds = com.tryden.simplenfl.network.response.models.roster.AlternateIds(),
    val birthPlace: com.tryden.simplenfl.network.response.models.roster.BirthPlace = com.tryden.simplenfl.network.response.models.roster.BirthPlace(),
    val contracts: List<Any> = listOf(),
    val dateOfBirth: String = "",
    val debutYear: Int = 0,
    val displayHeight: String = "",
    val displayName: String = "",
    val displayWeight: String = "",
    val experience: com.tryden.simplenfl.network.response.models.roster.Experience = com.tryden.simplenfl.network.response.models.roster.Experience(),
    val firstName: String = "",
    val fullName: String = "",
    val guid: String = "",
    val headshot: com.tryden.simplenfl.network.response.models.roster.Headshot = com.tryden.simplenfl.network.response.models.roster.Headshot(),
    val height: Int = 0,
    val id: String = "",
    val injuries: List<com.tryden.simplenfl.network.response.models.roster.Injury> = listOf(),
    val jersey: String = "",
    val lastName: String = "",
    val links: List<com.tryden.simplenfl.network.response.models.roster.Link> = listOf(),
    val position: com.tryden.simplenfl.network.response.models.roster.Position = com.tryden.simplenfl.network.response.models.roster.Position(),
    val shortName: String = "",
    val slug: String = "",
    val status: com.tryden.simplenfl.network.response.models.roster.Status = com.tryden.simplenfl.network.response.models.roster.Status(),
    val uid: String = "",
    val weight: Int = 0
)