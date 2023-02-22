package com.tryden.simplenfl.network.response.models.team

data class StatusType(
    val completed: Boolean = false,
    val description: String = "",
    val detail: String = "",
    val id: String = "",
    val name: String = "",
    val shortDetail: String = "",
    val state: String = ""
)