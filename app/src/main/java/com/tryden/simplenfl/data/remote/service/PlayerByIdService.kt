package com.tryden.simplenfl.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerByIdService {
    @GET("{player-id}")
    suspend fun getPlayerById(
        @Path("player-id") playerId: String
    )
}