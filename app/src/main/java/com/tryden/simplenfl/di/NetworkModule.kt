package com.tryden.simplenfl.di

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


}