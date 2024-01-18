package com.tryden.simplenfl.ui.repositories

import com.tryden.simplenfl.network.NetworkLayer
import com.tryden.simplenfl.data.remote.dto.ScoreboardDto

class ScoresRepository {

    suspend fun getScores(date: String, limit: String) : ScoreboardDto? {
        val request = NetworkLayer.apiClient.getScoresRange(date, limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }

    suspend fun getScoresCalendar(limit: String) : ScoreboardDto? {
        val request = NetworkLayer.apiClient.getScoresCalendar(limit)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}