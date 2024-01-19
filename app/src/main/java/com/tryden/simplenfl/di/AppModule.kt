package com.tryden.simplenfl.di

import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindDataRepository(dataRepository: DataRepositoryImpl): DataRepository


    // TODO: Mappers

}