package com.esmaeel.challenge.domain.repositories

import com.esmaeel.challenge.data.remote.models.Currency
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getCurrenciesList(): Flow<List<Currency>>
}