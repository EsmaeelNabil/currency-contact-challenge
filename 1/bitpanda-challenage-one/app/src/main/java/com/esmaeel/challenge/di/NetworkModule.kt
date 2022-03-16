package com.esmaeel.challenge.di

import com.esmaeel.challenge.data.remote.DataProvider
import com.esmaeel.challenge.data.remote.IRemoteService
import com.esmaeel.challenge.data.remote.RemoteService
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

}
