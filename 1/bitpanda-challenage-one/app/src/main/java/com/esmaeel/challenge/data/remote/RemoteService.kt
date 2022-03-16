package com.esmaeel.challenge.data.remote

import com.esmaeel.challenge.data.remote.models.Asset
import com.esmaeel.challenge.data.remote.models.Currency
import com.esmaeel.challenge.data.remote.models.CurrencyWallet
import com.esmaeel.challenge.data.remote.models.Fiat
import javax.inject.Inject

class RemoteService @Inject constructor(private val dataProvider: DataProvider) : IRemoteService {

    override fun getCryptoWallets(): List<CurrencyWallet> =
        dataProvider.getDummyCryptoWalletList()


    override fun getMetalWallets(): List<CurrencyWallet> =
        dataProvider.getDummyMetalWalletList()


    override fun getFiatWallets(): List<CurrencyWallet> = dataProvider.getDummyEURWallet()

    override fun getCryptoCoins(): List<Asset> = dataProvider.getCryptoCoins()

    override fun getMetals(): List<Asset> = dataProvider.getMetals()

    override fun getFiats(): List<Fiat> = dataProvider.getFiats()

    override fun getCurrencies(): List<Currency> = dataProvider.getCurrencyList()

}