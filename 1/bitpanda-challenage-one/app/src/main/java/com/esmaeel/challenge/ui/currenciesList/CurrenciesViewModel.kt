package com.esmaeel.challenge.ui.currenciesList

import com.esmaeel.challenge.common.base.BaseViewModel
import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.di.ContextProvider
import com.esmaeel.challenge.domain.usecases.currencies.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    contextProvider: ContextProvider
) : BaseViewModel(contextProvider) {

    fun getCurrenciesList(filterType: CurrencyType) = launchBlock {
        getCurrenciesUseCase.invoke(filterType).collect {
            setState(
                if (it.isEmpty())
                    ViewState.Empty
                else
                    CurrenciesListViewState.OnCurrenciesListResponse(it)
            )
        }
    }
}