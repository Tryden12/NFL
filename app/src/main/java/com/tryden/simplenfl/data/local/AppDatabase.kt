package com.tryden.simplenfl.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tryden.simplenfl.data.local.dao.FavoriteTeamDao
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity


/**
 * This class is to create Dog Breeds database.
 */
@Database(
    entities = [FavoriteTeamEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteTeamDao(): FavoriteTeamDao
}