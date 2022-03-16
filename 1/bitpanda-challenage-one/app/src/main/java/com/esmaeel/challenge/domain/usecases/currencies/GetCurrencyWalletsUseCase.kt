package com.esmaeel.challenge.domain.usecases.currencies


import com.esmaeel.challenge.data.filterByAvailableWallets
import com.esmaeel.challenge.data.remote.models.Currency
import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.data.repositories.Repository
import com.esmaeel.challenge.data.sortedByOrder
import com.esmaeel.challenge.di.ContextProvider
import com.esmaeel.challenge.domain.usecases.UseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton


// TODO: Test me
/*
* 1- get a list of Currencies
* 2- filter Currencies by type
* 3- filter it's wallets to not show deleted wallets
* 4- Sort the list by type : fiat, cryptoCoins, metals and the balance of the wallet
*/
@Singleton
class GetCurrenciesUseCase @Inject constructor(
    private val repo: Repository,
    private val contextProvider: ContextProvider,
) : UseCase<Flow<List<Currency>>, CurrencyType> {

    override suspend fun invoke(input: CurrencyType): Flow<List<Currency>> {
        val currencies =
            repo.getCurrenciesList().firstOrNull() ?: return flowOf(emptyList())
        // make our processing on the IO thread
        return flow {
            val currenciesByType =
                if (input is CurrencyType.ALL) currencies else currencies.filter { it.type == input.typeId }
            val currenciesWithOnlyAvailableWallets = currenciesByType.filterByAvailableWallets()
            val sortedCurrencies = currenciesWithOnlyAvailableWallets.sortedByOrder()
            emit(sortedCurrencies)
        }.flowOn(contextProvider.IO)
    }

}





