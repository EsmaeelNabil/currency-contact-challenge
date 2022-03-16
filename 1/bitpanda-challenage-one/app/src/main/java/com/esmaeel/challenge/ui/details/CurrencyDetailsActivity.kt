package com.esmaeel.challenge.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.esmaeel.challenge.data.remote.models.Currency
import com.esmaeel.challenge.databinding.DetailsActivityBinding
import com.esmaeel.challenge.ui.currenciesList.WalletsAdapter
import com.esmaeel.challenge.utils.ktx.gone
import com.skydoves.bundler.bundle

class CurrencyDetailsActivity : AppCompatActivity() {
    private val binder by lazy {
        DetailsActivityBinding.inflate(layoutInflater)
    }

    private val data: Currency? by bundle(CURRENCY)

    companion object {
        const val CURRENCY = "currency"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        binder.back.setOnClickListener { onBackPressed() }
        data?.let {
            val walletsAdapter =
                WalletsAdapter(currencyItem = it)
            with(binder) {
                binder.currencyInclude.walletsRecycler.adapter = walletsAdapter
                walletsAdapter.submitList(it.wallet)
                nameTv.text = it.getName()
                currencyInclude.nameTv.text = it.getName()
                currencyInclude.symbolTv.text = it.getSymbol()
                currencyInclude.image.load(it.getLogo())
                currencyInclude.priceTv.text = it.getPrice().ifEmpty {
                    currencyInclude.priceTv.gone()
                    ""
                }
            }
        }


    }
}