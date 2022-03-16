package com.esmaeel.challenge.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val remoteService: RemoteService) {
    fun getCurrenciesList() = remoteService.getCurrencies()
}