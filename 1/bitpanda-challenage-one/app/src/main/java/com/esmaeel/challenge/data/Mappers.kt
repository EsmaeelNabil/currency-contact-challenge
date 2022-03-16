package com.esmaeel.challenge.data

import com.esmaeel.challenge.data.remote.models.AssetType
import com.esmaeel.challenge.data.remote.models.Currency
import com.esmaeel.challenge.data.remote.models.CurrencyType

// TODO: Test me
/**
 * filter it's wallets to not show deleted wallets
 */
fun List<Currency>.filterByAvailableWallets(): List<Currency> = this.map { currency ->
    val notDeletedWallets = currency.wallet.filter { it.deleted.not() }
    currency.copy(wallet = notDeletedWallets)
}


// TODO: Test me
/**
 * Sort the list by type : fiat, cryptoCoins, metals and the balance of the wallet
 */
fun List<Currency>.sortedByOrder(): List<Currency> {
    val sortedList = arrayListOf<Currency>()
    val fiats = this.filter { it.type == CurrencyType.FiatCurrency.typeId }
    val assets = this.filter { it.type == CurrencyType.AssetCurrency.typeId }
    val cryptoCoins = assets.filter { it.assetData.type == AssetType.CryptoCoinAsset.typeId }
    val metals = assets.filter { it.assetData.type == AssetType.CryptoCoinAsset.typeId }
    sortedList.addAll(fiats.sortedByWalletBalance())
    sortedList.addAll(cryptoCoins.sortedByWalletBalance())
    sortedList.addAll(metals.sortedByWalletBalance())
    return sortedList
}

// TODO: Test me
/**
 *  sort depending on the balance of the wallet
 */
fun List<Currency>.sortedByWalletBalance(): List<Currency> = this.map { currency ->
    val orderedByBalanceWallets = currency.wallet.sortedByDescending { it.balance }
    currency.copy(wallet = orderedByBalanceWallets)
}