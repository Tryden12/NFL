package com.tryden.simplenfl.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.simplenfl.data.remote.service.NFLService
import com.tryden.simplenfl.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
    fun provideNflApi(retrofitFactory: RetrofitFactory, moshi: Moshi,): NFLService =
        retrofitFactory.create(Constants.BASE_URL_NFL, moshi).build()

}