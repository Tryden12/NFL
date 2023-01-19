package com.tryden.simplenfl.network.service

import com.tryden.simplenfl.network.response.teams.models.player.Player
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerByIdService {
    @GET("{player-id}")
    suspend fun getPlayerById(
        @Path("player-id") playerId: String
    ): Response<Player>
}