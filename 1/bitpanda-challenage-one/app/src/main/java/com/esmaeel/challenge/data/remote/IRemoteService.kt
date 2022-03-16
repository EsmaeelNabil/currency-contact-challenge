package com.esmaeel.challenge.data.remote

import com.esmaeel.challenge.data.remote.models.*

/**
 * so we can swap implementations in unit-testing
 */
interface IRemoteService {
    fun getCryptoWallets(): List<CurrencyWallet>
    fun getMetalWallets(): List<CurrencyWallet>
    fun getFiatWallets(): List<CurrencyWallet>
    fun getCryptoCoins(): List<Asset>
    fun getMetals(): List<Asset>
    fun getFiats(): List<Fiat>
    fun getCurrencies(): List<Currency>
}