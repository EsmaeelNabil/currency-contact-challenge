package com.esmaeel.challenge.domain.usecases.currencies

import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.data.repositories.Repository
import com.esmaeel.challenge.utils.TestContextProvider
import com.esmaeel.challenge.utils.dataProvider
import com.esmaeel.challenge.utils.getSortedData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class GetCurrenciesUseCaseTest {

    lateinit var useCase: GetCurrenciesUseCase
    private val contextProvidersTest = TestContextProvider()

    @MockK
    lateinit var repo: Repository

    @MockK
    lateinit var fakeResponse: List<Currency>

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetCurrenciesUseCase(repo = repo, contextProvider = contextProvidersTest)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get All currencies list with default sorting in the useCase`() = runBlockingTest {
        coEvery { repo.getCurrenciesList() } returns flow { emit(dataProvider.getCurrencyList()) }

        useCase.invoke(CurrencyType.ALL).collect {
            assertEquals(it, getSortedData(CurrencyType.ALL).first())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get Assets currencies list only with default sorting in the useCase`() = runBlockingTest {
        coEvery { repo.getCurrenciesList() } returns flow { emit(dataProvider.getCurrencyList()) }

        useCase.invoke(CurrencyType.AssetCurrency).collect {
            assertEquals(it, getSortedData(CurrencyType.AssetCurrency).first())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get Fiats currencies list only with default sorting in the useCase`() = runBlockingTest {
        coEvery { repo.getCurrenciesList() } returns flow { emit(dataProvider.getCurrencyList()) }

        useCase.invoke(CurrencyType.FiatCurrency).collect {
            assertEquals(it, getSortedData(CurrencyType.FiatCurrency).first())
        }
    }
}