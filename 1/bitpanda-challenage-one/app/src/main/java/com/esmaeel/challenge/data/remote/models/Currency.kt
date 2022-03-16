package com.esmaeel.challenge.data.remote.models

import com.esmaeel.challenge.utils.ktx.getPrice
import java.io.Serializable

/*
* CryptoCoin and Metal are considered as Asset
* Fiat and Asset are considered as Currency
* Each Currency has a Wallet
* and you can have multiple Wallets per Currency
* */

sealed class AssetType(val typeId: Int) {
    object CryptoCoinAsset : AssetType(1)
    object MetalAsset : AssetType(2)
}

data class Asset(
    val type: Int = AssetType.CryptoCoinAsset.typeId,
    val name: String = "",
    val symbol: String = "",
    val id: String = "",
    val price: Double = 0.0,
    val logo: String = ""
) : Serializable {
    val precision: Int
        get() = when (type) {
            AssetType.CryptoCoinAsset.typeId -> 4
            AssetType.MetalAsset.typeId -> 3
            else -> 4
        }
}


data class Fiat(
    var precision: Int = 2,
    var name: String = "",
    var symbol: String = "",
    var id: String = "",
    var logo: String = "",
) : Serializable


sealed class CurrencyType(val typeId: Int, val name: String) {
    object FiatCurrency : CurrencyType(3, "Fait")
    object AssetCurrency : CurrencyType(4, "Assets")
    object ALL : CurrencyType(34, "ALL")
}

data class Currency(
    val type: Int = CurrencyType.ALL.typeId,
    val wallet: List<CurrencyWallet> = listOf(),
    val fiatData: Fiat = Fiat(),
    val assetData: Asset = Asset()
) : Serializable {
    fun getLogo() = when (type) {
        CurrencyType.FiatCurrency.typeId -> fiatData.logo
        CurrencyType.AssetCurrency.typeId -> assetData.logo
        else -> ""
    }

    fun getName() = when (type) {
        CurrencyType.AssetCurrency.typeId -> assetData.name
        CurrencyType.FiatCurrency.typeId -> fiatData.name
        else -> ""
    }

    fun getSymbol() = when (type) {
        CurrencyType.FiatCurrency.typeId -> fiatData.symbol
        CurrencyType.AssetCurrency.typeId -> assetData.symbol
        else -> ""
    }

    fun getPrice() = when (type) {
        CurrencyType.FiatCurrency.typeId -> ""
        CurrencyType.AssetCurrency.typeId -> assetData.price.getPrice(
            assetData.precision,
            assetData.symbol
        )
        else -> ""
    }
}