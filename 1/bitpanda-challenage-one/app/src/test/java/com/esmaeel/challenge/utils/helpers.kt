package com.esmaeel.challenge.utils

import com.esmaeel.challenge.data.filterByAvailableWallets
import com.esmaeel.challenge.data.remote.DataProvider
import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.data.sortedByOrder
import kotlinx.coroutines.flow.flow

val dataProvider: DataProvider = DataProvider()


fun getSortedData(currencyType: CurrencyType) = flow {
    val currencies = dataProvider.getCurrencyList()
    val currenciesByType =
        if (currencyType is CurrencyType.ALL) currencies else currencies.filter { it.type == currencyType.typeId }
    val currenciesWithOnlyAvailableWallets =
        currenciesByType.filterByAvailableWallets()
    val sortedCurrencies = currenciesWithOnlyAvailableWallets.sortedByOrder()
    emit(sortedCurrencies)
}