package com.esmaeel.challenge.data

import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.utils.dataProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@RunWith(MockitoJUnitRunner::class)
class MappersKtTest {


    @Test
    fun filterByAvailableWallets() {
        val currencies = dataProvider.getCurrencyList()
        val filtered = currencies.filterByAvailableWallets()

        filtered.forEach { currency ->
            currency.wallet.forEach {
                assertFalse(it.deleted)
            }
        }
    }


    @Test
    fun `sortedByOrder fiat, cryptoCoins, metals`() {
        val currencies = dataProvider.getCurrencyListUnsorted()
        val filtered = currencies.sortedByOrder()

        assertEquals(filtered[0].type, CurrencyType.FiatCurrency.typeId)
        assertEquals(filtered[1].type, CurrencyType.AssetCurrency.typeId)
    }

    @Test
    fun sortedByWalletBalance() {
        val currencies = dataProvider.getCurrencyList()
        val sorted = currencies.sortedByWalletBalance()


        val expected = sorted.map { currency ->
            val orderedByBalanceWallets = currency.wallet.sortedByDescending { it.balance }
            currency.copy(wallet = orderedByBalanceWallets)
        }

        assertEquals(sorted, expected)

    }
}