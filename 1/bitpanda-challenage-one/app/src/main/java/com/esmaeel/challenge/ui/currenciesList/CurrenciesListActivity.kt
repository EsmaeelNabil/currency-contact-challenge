package com.esmaeel.challenge.ui.currenciesList

import androidx.activity.viewModels
import com.esmaeel.challenge.R
import com.esmaeel.challenge.common.base.BaseActivity
import com.esmaeel.challenge.common.base.ViewState
import com.esmaeel.challenge.data.remote.models.Currency
import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.databinding.CurrenciesListActivityBinding
import com.esmaeel.challenge.ui.details.CurrencyDetailsActivity
import com.skydoves.bundler.intentOf
import dagger.hilt.android.AndroidEntryPoint

val currenciesTypes = listOf(
    CurrencyType.ALL,
    CurrencyType.AssetCurrency,
    CurrencyType.FiatCurrency,
)

@AndroidEntryPoint
class CurrenciesListActivity :
    BaseActivity<CurrenciesListActivityBinding, CurrenciesViewModel>(R.layout.currencies_list_activity) {

    override val viewModel: CurrenciesViewModel by viewModels()
    private var latestSelectedFilter: CurrencyType = CurrencyType.ALL

    private val currenciesListAdapter by lazy {
        CurrenciesListAdapter(
            onCurrencyClicked = { currency, _ -> openDetails(currency) }
        )
    }

    private val filterAdapter by lazy {
        FilterAdapter(onFilterClicked = { item: CurrencyType, _: Int ->
            getData(item)
        })
    }

    override fun setup() = with(binder) {
        includedToolbar.filterRecycler.adapter = filterAdapter
        currenciesRecycler.adapter = currenciesListAdapter
        getData()
        bindFilters(currenciesTypes)
        swipeToRefresh.setOnRefreshListener {
            swipeToRefresh.isRefreshing = false
            getData()
        }
    }

    /**
     * cache the latest filter for (refreshing or pull refresh)
     */
    private fun getData(filterType: CurrencyType = latestSelectedFilter) {
        latestSelectedFilter = filterType
        viewModel.getCurrenciesList(filterType)
    }


    override fun render(state: ViewState) {
        when (state) {
            is CurrenciesListViewState.OnCurrenciesListResponse -> {
                // only bind the new data if it exists.
                // and keep the old data in case or refresh errors.
                if (state.data.isNullOrEmpty().not()) {
                    bindList(state.data)
                }
                //else render empty view
            }

            is ViewState.Error -> handleError(state.error)
        }
    }

    private fun handleError(error: String?) {
        error?.let { showError(it) }
    }

    private fun bindList(products: List<Currency>) {
        currenciesListAdapter.submitList(null) {
            currenciesListAdapter.submitList(products)
        }
    }

    private fun bindFilters(filters: List<CurrencyType>) {
        filterAdapter.submitList(filters)
    }

    private fun openDetails(item: Currency) {
        intentOf<CurrencyDetailsActivity> {
            putExtra(CurrencyDetailsActivity.CURRENCY, item)
            startActivity(this@CurrenciesListActivity)
        }
    }

}