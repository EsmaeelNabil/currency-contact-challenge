package com.esmaeel.challenge.di

import com.esmaeel.challenge.data.remote.DataProvider
import com.esmaeel.challenge.data.remote.IRemoteService
import com.esmaeel.challenge.data.remote.RemoteDataSource
import com.esmaeel.challenge.data.remote.RemoteService
import com.esmaeel.challenge.data.repositories.Repository
import com.esmaeel.challenge.domain.repositories.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRemoteService(dataProvider: DataProvider): IRemoteService {
        return RemoteService(dataProvider)
    }

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        contextProvider: ContextProvider,
        resourcesHandler: ResourcesHandler
    ): IRepository {
        return Repository(
            remoteDataSource = remoteDataSource,
            contextProvider = contextProvider,
            resourcesHandler = resourcesHandler
        )
    }

}
