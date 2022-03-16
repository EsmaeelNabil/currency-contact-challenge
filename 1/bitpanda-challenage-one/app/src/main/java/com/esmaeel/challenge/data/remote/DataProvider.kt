package com.esmaeel.challenge.data.remote

import com.esmaeel.challenge.data.remote.models.*
import javax.inject.Inject
import javax.inject.Singleton

/*
* now the data is being created only if requested, not always in the ram.
* */
@Singleton
class DataProvider @Inject constructor() {

    fun getDummyMetalWalletList() = listOf(
        CurrencyWallet(
            typeId = "4",
            type = WalletType.AssetWallet.typeId,
            id = "1",
            name = "Gold Wallet 1",
            balance = 133.729,
            isDefault = true,
            deleted = false
        ),
        CurrencyWallet(
            type = WalletType.AssetWallet.typeId,
            id = "2",
            name = "Gold Wallet 2",
            balance = 2043.4340,
            isDefault = false,
            typeId = "4",
            deleted = false
        ),
        CurrencyWallet(
            type = WalletType.AssetWallet.typeId,
            id = "2",
            name = "Test Palladium Wallet",
            balance = 200.0,
            isDefault = false,
            typeId = "5",
            deleted = false
        )
    )

    fun getDummyCryptoWalletList() = listOf(
        CurrencyWallet(
            type = WalletType.AssetWallet.typeId,
            id = "1",
            name = "Test BTC Wallet",
            balance = 133.7,
            isDefault = false,
            typeId = "1",
            deleted = false
        ),
        CurrencyWallet(
            type = WalletType.AssetWallet.typeId,
            id = "2",
            name = "Test BTC Wallet 2",
            balance = 0.0,
            isDefault = false,
            typeId = "1",
            deleted = true
        ),
        CurrencyWallet(
            type = WalletType.AssetWallet.typeId,
            id = "3",
            name = "Test BEST Wallet",
            balance = 1032.23,
            isDefault = false,
            typeId = "2",
            deleted = false
        ),
        CurrencyWallet(
            type = WalletType.AssetWallet.typeId,
            id = "4",
            name = "Test Ripple Wallet",
            balance = 2304.04,
            isDefault = false,
            typeId = "3",
            deleted = false
        )
    )

    fun getDummyEURWallet() = listOf(
        CurrencyWallet(
            type = WalletType.FiatWallet.typeId,
            id = "1",
            name = "EUR Wallet",
            typeId = "1",
            balance = 400.0,
            isDefault = false,
            deleted = false
        ),
        CurrencyWallet(
            type = WalletType.FiatWallet.typeId,
            id = "2",
            name = "CHF Wallet",
            typeId = "2",
            balance = 0.0,
            isDefault = false,
            deleted = false
        )
    )

    fun getCryptoCoins(): List<Asset> = listOf(
        Asset(
            type = AssetType.CryptoCoinAsset.typeId,
            name = "Bitcoin",
            symbol = "BTC",
            id = "1",
            price = 9000.0,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/btc.svg"
        ),
        Asset(
            type = AssetType.CryptoCoinAsset.typeId,
            name = "Bitpanda Ecosystem Token",
            symbol = "BEST",
            id = "2",
            price = 0.03,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/best.svg"
        ),
        Asset(
            type = AssetType.CryptoCoinAsset.typeId,
            name = "Ripple",
            symbol = "XRP",
            id = "3",
            price = 0.2119,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xrp.svg"
        )
    )

    fun getFiats(): List<Fiat> = listOf(
        Fiat(
            name = "Euro",
            symbol = "EUR",
            id = "1",
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/usd.svg"
        ),
        Fiat(
            name = "Swiss Franc",
            symbol = "CHF",
            id = "2",
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/chf.svg"
        )
    )

    fun getMetals(): List<Asset> = listOf(
        Asset(
            type = AssetType.MetalAsset.typeId,
            name = "Gold",
            symbol = "XAU",
            id = "4",
            price = 45.14,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xau.svg"
        ),
        Asset(
            type = AssetType.MetalAsset.typeId,
            name = "Palladium",
            symbol = "XPD",
            id = "5",
            price = 70.40,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xpd.svg"
        )
    )

    /*
    * CryptoCoin and Metal are considered as Asset
    * Fiat and Asset are considered as Currency
    * Each Currency has a Wallet
    * and you can have multiple Wallets per Currency
    * */
    fun getCurrencyList(): List<Currency> = listOf(
        Currency(
            type = CurrencyType.FiatCurrency.typeId,
            fiatData = getFiats()[0],
            wallet = getDummyEURWallet()
        ),
        Currency(
            type = CurrencyType.FiatCurrency.typeId,
            fiatData = getFiats()[1],
            wallet = getDummyEURWallet()
        ),
        Currency(
            type = CurrencyType.AssetCurrency.typeId,
            assetData = getCryptoCoins()[0],
            wallet = getDummyCryptoWalletList()
        ),
        Currency(
            type = CurrencyType.AssetCurrency.typeId,
            assetData = getCryptoCoins()[1],
            wallet = getDummyCryptoWalletList()
        ),
        Currency(
            type = CurrencyType.AssetCurrency.typeId,
            assetData = getCryptoCoins()[2],
            wallet = getDummyCryptoWalletList()
        ),
        Currency(
            type = CurrencyType.AssetCurrency.typeId,
            assetData = getMetals()[0],
            wallet = getDummyMetalWalletList()
        ),
        Currency(
            type = CurrencyType.AssetCurrency.typeId,
            assetData = getMetals()[1],
            wallet = getDummyMetalWalletList()
        ),
    )


    fun getCurrencyListUnsorted(): List<Currency> = listOf(
        Currency(
            type = CurrencyType.AssetCurrency.typeId,
            assetData = getCryptoCoins()[0],
            wallet = getDummyCryptoWalletList()
        ),
        Currency(
            type = CurrencyType.AssetCurrency.typeId,
            assetData = getMetals()[0],
            wallet = getDummyMetalWalletList()
        ),
        Currency(
            type = CurrencyType.FiatCurrency.typeId,
            fiatData = getFiats()[0],
            wallet = getDummyEURWallet()
        )
    )

}
