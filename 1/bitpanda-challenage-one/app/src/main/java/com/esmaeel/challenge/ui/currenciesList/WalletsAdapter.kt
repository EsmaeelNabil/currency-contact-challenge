package com.esmaeel.challenge.ui.currenciesList

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.esmaeel.challenge.data.remote.models.Currency
import com.esmaeel.challenge.data.remote.models.CurrencyWallet
import com.esmaeel.challenge.data.remote.models.WalletType
import com.esmaeel.challenge.databinding.WalletItemBinding
import com.esmaeel.challenge.utils.ktx.getPrice
import com.esmaeel.challenge.utils.ktx.layoutInflator


class WalletsAdapter(
    private val onWalletClicked: (item: CurrencyWallet, position: Int) -> Unit = { _, _ -> },
    private val currencyItem: Currency,
) : ListAdapter<CurrencyWallet, WalletsAdapter.WalletViewHolder>(ItemDiffUtil) {

    private object ItemDiffUtil : DiffUtil.ItemCallback<CurrencyWallet>() {
        override fun areItemsTheSame(oldItem: CurrencyWallet, newItem: CurrencyWallet) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CurrencyWallet, newItem: CurrencyWallet) =
            areItemsTheSame(oldItem, newItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WalletsAdapter.WalletViewHolder {
        return WalletViewHolder(
            WalletItemBinding.inflate(
                parent.layoutInflator,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WalletsAdapter.WalletViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WalletViewHolder(private val binder: WalletItemBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bind(item: CurrencyWallet) = with(binder) {
            root.setOnClickListener { onWalletClicked(item, absoluteAdapterPosition) }
            when (item.type) {
                /*for Metals show the name in the list eg.: "Gold" */
                WalletType.AssetWallet.typeId -> renderAsset(item)
                else -> render(item)
            }
        }

        private fun render(item: CurrencyWallet) = with(binder) {
            image.load(currencyItem.getLogo())
            symbolTv.text = currencyItem.getSymbol()
            priceTv.text = item.balance.getPrice()
        }

        /*for Metals show the name in the list eg.: "Gold"*/
        private fun renderAsset(assetData: CurrencyWallet) = with(binder) {
            image.load(currencyItem.getLogo())
            symbolTv.text = assetData.name
            priceTv.text = assetData.balance.getPrice()
        }

    }


}

