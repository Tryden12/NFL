package com.tryden.simplenfl.database.dao

import androidx.room.*
import com.tryden.simplenfl.database.entity.FavoriteTeamEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteTeamDao {

    @Query("SELECT * FROM favorite_team_entity")
    fun getAllFavoriteTeams(): Flow<List<FavoriteTeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteTeamEntity: FavoriteTeamEntity)

    @Delete
    fun delete(favoriteTeamEntity: FavoriteTeamEntity)

    @Update
    fun update(favoriteTeamEntity: FavoriteTeamEntity)
}