package com.tryden.simplenfl.network.response.models.news


data class Category(
    val description: String = "",
    val id: Int = 0,
    val sportId: Int = 0,
    val teamId: Int? = 0,
    val topicId: Int? = 0,
    val type: String = "",
)