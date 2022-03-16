package com.esmaeel.challenge.ui.currenciesList

import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.data.remote.models.Currency

sealed class CurrenciesListViewState() : ViewState() {
    data class OnCurrenciesListResponse(val data: List<Currency>) :
        Loaded<List<Currency>>(data)
}