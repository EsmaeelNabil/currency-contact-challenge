package com.esmaeel.challenge.data.repositories

import com.esmaeel.challenge.common.base.BaseRepository
import com.esmaeel.challenge.data.remote.RemoteDataSource
import com.esmaeel.challenge.di.ContextProvider
import com.esmaeel.challenge.di.ResourcesHandler
import com.esmaeel.challenge.domain.repositories.IRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * remoteDataSource for future caching.
 * contextProvider for unifying contexts in unit-test.
 * resourcesHandler for passing specific (string.xml) massages.
 */
@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    contextProvider: ContextProvider,
    resourcesHandler: ResourcesHandler
) : BaseRepository(contextProvider, resourcesHandler), IRepository {

    override fun getCurrenciesList() = networkHandler {
        remoteDataSource.getCurrenciesList()
    }

}