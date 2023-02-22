package com.tryden.simplenfl.network.response.models.article

data class ImageRelated(
    val alt: String? = "",
    val caption: String? = "",
    val credit: String = "",
    val height: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val type: String = "",
    val url: String = "",
    val width: Int = 0
)