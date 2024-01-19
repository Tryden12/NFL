package com.tryden.simplenfl.di

import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.data.repository.DataRepository
import com.tryden.simplenfl.data.repository.DataRepositoryImpl
import com.tryden.simplenfl.domain.newmapper.Mapper
import com.tryden.simplenfl.domain.newmapper.TeamsListMapper
import com.tryden.simplenfl.domain.newmodels.TeamList
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    /**
     * Repository
     */
    @Binds
    @Singleton
    fun bindDataRepository(dataRepository: DataRepositoryImpl): DataRepository


    /**
     * Mappers
     */
    @Binds
    @Singleton
    fun bindTeamsListMapper(mapper: TeamsListMapper): Mapper<TeamList, AllTeamsDto.Team>
}