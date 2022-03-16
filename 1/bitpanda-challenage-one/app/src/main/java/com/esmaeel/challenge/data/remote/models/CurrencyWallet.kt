package com.esmaeel.challenge.data.remote.models

import java.io.Serializable

sealed class WalletType(val typeId: Int) {
    object FiatWallet : WalletType(5)
    object AssetWallet : WalletType(6)
}

data class CurrencyWallet(
    var type: Int = WalletType.FiatWallet.typeId,
    var id: String = "",
    var typeId: String = "",
    var isDefault: Boolean = false,
    var balance: Double = 0.0,
    var deleted: Boolean = false,
    var name: String = ""
) : Serializable {

    fun reduceBalance(amount: Double) {
        balance = balance.minus(amount)
        if (balance < 0.0)
            balance = 0.0
    }

    fun addBalance(amount: Double) {
        balance = balance.plus(amount)
    }

}