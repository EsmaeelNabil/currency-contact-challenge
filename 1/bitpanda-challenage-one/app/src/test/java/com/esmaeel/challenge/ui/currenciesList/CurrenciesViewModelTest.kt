package com.esmaeel.challenge.ui.currenciesList

import app.cash.turbine.test
import com.esmaeel.challenge.common.base.AppException
import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.domain.usecases.currencies.GetCurrenciesUseCase
import com.esmaeel.challenge.utils.TestContextProvider
import com.esmaeel.challenge.utils.getSortedData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotEquals


@RunWith(MockitoJUnitRunner::class)
class CurrenciesViewModelTest {

    private lateinit var viewModel: CurrenciesViewModel
    private val contextProvidersTest = TestContextProvider()

    @MockK
    lateinit var useCase: GetCurrenciesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CurrenciesViewModel(
            getCurrenciesUseCase = useCase,
            contextProvider = contextProvidersTest
        )
    }

    @Test
    fun `get All currencies with non empty Data as OnCurrenciesListResponse state`() =
        runBlocking {
            coEvery { useCase.invoke(CurrencyType.ALL) } returns getSortedData(CurrencyType.ALL)

            viewModel.getCurrenciesList(CurrencyType.ALL)

            viewModel.state.test {
                val data = awaitItem()
                assertIs<CurrenciesListViewState.OnCurrenciesListResponse>(data)
                assertEquals(data.data, getSortedData(CurrencyType.ALL).first())
                cancelAndIgnoreRemainingEvents()
            }

        }

    @Test
    fun `get Only Fiat currencies with non empty Data as OnCurrenciesListResponse state`() =
        runBlocking {
            coEvery { useCase.invoke(CurrencyType.FiatCurrency) } returns getSortedData(CurrencyType.FiatCurrency)

            viewModel.getCurrenciesList(CurrencyType.FiatCurrency)

            viewModel.state.test {
                val data = awaitItem()
                assertIs<CurrenciesListViewState.OnCurrenciesListResponse>(data)
                assertEquals(
                    data.data,
                    getSortedData(currencyType = CurrencyType.FiatCurrency).first()
                )
                assertNotEquals(
                    data.data,
                    getSortedData(currencyType = CurrencyType.AssetCurrency).first()
                )
                cancelAndIgnoreRemainingEvents()
            }

        }


    @Test
    fun `get All currencies with empty Data as ViewStateEmpty state`() =
        runBlocking {
            coEvery { useCase.invoke(CurrencyType.ALL) } returns flow {
                /*    val currencies = dataProvider.getCurrencyList()
                    val currenciesWithOnlyAvailableWallets =
                        currencies.filterByAvailableWallets()
                    val sortedCurrencies = currenciesWithOnlyAvailableWallets.sortedByOrder()*/
                emit(emptyList())
            }

            viewModel.getCurrenciesList(CurrencyType.ALL)

            viewModel.state.test {
                assertIs<ViewState.Empty>(awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

        }

    @Test
    fun `get currencies with exception as ViewState Error state`() =
        runBlocking {
            coEvery { useCase.invoke(CurrencyType.ALL) } returns flow {
                throw AppException("use case error")
            }

            viewModel.getCurrenciesList(CurrencyType.ALL)

            viewModel.state.test {
                val error = awaitItem()
                assertIs<ViewState.Error>(error)
                assertEquals(error.error, "use case error")
                cancelAndIgnoreRemainingEvents()
            }

        }
}