package com.tryden.simplenfl.network.response.teams.models.news

data class Api(
    val news: NewsX = NewsX(),
    val self: Self = Self()
)