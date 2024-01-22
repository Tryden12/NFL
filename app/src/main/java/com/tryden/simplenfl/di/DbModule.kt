package com.tryden.simplenfl.di

import android.content.Context
import androidx.room.Room
import com.tryden.simplenfl.data.local.AppDatabase
import com.tryden.simplenfl.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * This is the Hilt DbModule class which provides our database and dao dependency
 * wherever it is required.
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    /**
     * Tells Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent.
     * (i.e. everywhere in the application)
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        DB_NAME
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideFavoriteTeamDao(db: AppDatabase) = db.favoriteTeamDao()
}