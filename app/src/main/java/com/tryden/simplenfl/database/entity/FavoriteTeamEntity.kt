package com.tryden.simplenfl.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_team_entity")
data class FavoriteTeamEntity(
    @PrimaryKey val id: String = "",
    val shortName: String = "",
    val longName: String = "",
    val abbreviation: String = "",
    val color: String = "",
    val logo: String = ""
)