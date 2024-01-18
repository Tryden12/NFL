package com.tryden.simplenfl.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tryden.simplenfl.data.local.dao.FavoriteTeamDao
import com.tryden.simplenfl.data.local.entity.FavoriteTeamEntity


@Database(
    entities = [FavoriteTeamEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (appDatabase != null) {
                return appDatabase!!
            }

            appDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                name = "simple-nfl-database"
            )
                .build()
            return appDatabase!!
        }
    }

    abstract fun favoriteTeamDao(): FavoriteTeamDao
}