package com.esmaeel.challenge.ui.currenciesList

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.esmaeel.challenge.data.remote.models.Currency
import com.esmaeel.challenge.databinding.CurrencyItemBinding
import com.esmaeel.challenge.utils.ktx.gone
import com.esmaeel.challenge.utils.ktx.layoutInflator


class CurrenciesListAdapter(
    private val onCurrencyClicked: (item: Currency, position: Int) -> Unit,
) : ListAdapter<Currency, CurrenciesListAdapter.CurrencyViewHolder>(ItemDiffUtil) {

    private object ItemDiffUtil : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency) =
            areItemsTheSame(oldItem, newItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrenciesListAdapter.CurrencyViewHolder {
        return CurrencyViewHolder(
            CurrencyItemBinding.inflate(
                parent.layoutInflator,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrenciesListAdapter.CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CurrencyViewHolder(private val binder: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bind(item: Currency) = with(binder) {
            val walletsAdapter =
                WalletsAdapter(
                    onWalletClicked = { _, p ->
                        onCurrencyClicked(item, p)
                    }, currencyItem = item
                )
            root.setOnClickListener { onCurrencyClicked(item, absoluteAdapterPosition) }
            walletsRecycler.adapter = walletsAdapter
            walletsAdapter.submitList(item.wallet)

            nameTv.text = item.getName()
            symbolTv.text = item.getSymbol()
            image.load(item.getLogo())
            priceTv.text = item.getPrice().ifEmpty {
                priceTv.gone()
                ""
            }
        }
    }


}

