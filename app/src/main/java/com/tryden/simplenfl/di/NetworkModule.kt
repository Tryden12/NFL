package com.tryden.simplenfl.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.simplenfl.data.local.dao.FavoriteTeamDao
import com.tryden.simplenfl.data.local.source.LocalDataSource
import com.tryden.simplenfl.data.local.source.LocalSource
import com.tryden.simplenfl.data.remote.service.ArticleService
import com.tryden.simplenfl.data.remote.service.NFLService
import com.tryden.simplenfl.data.remote.source.RemoteDataSource
import com.tryden.simplenfl.data.remote.source.RemoteSource
import com.tryden.simplenfl.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is the Hilt NetworkModule class which provides the following dependencies:
 * retrofit, client, service and repositories
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideNflApi(retrofitFactory: RetrofitFactory, moshi: Moshi,): NFLService =
        retrofitFactory.create(Constants.BASE_URL_NFL, moshi).build()

    @Provides
    @Singleton
    fun provideArticleService(retrofitFactory: RetrofitFactory, moshi: Moshi): ArticleService =
        retrofitFactory.create(Constants.ARTICLE_BY_ID_URL,moshi).build()

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        nflService: NFLService,
        articleService: ArticleService
    ): RemoteSource {
        return RemoteDataSource(nflService, articleService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: FavoriteTeamDao): LocalSource {
        return LocalDataSource(dao)
    }

}