package com.esmaeel.challenge.ui.currenciesList

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esmaeel.challenge.data.remote.models.CurrencyType
import com.esmaeel.challenge.utils.ktx.layoutInflator
import com.esmaeel.challenge.databinding.FilterItemBinding

// use selection library and add filter selection colors later
class FilterAdapter(
    private val onFilterClicked: (item: CurrencyType, position: Int) -> Unit,
) : ListAdapter<CurrencyType, FilterAdapter.ProductFilterItemViewHolder>(ItemDiffUtil) {

    private object ItemDiffUtil : DiffUtil.ItemCallback<CurrencyType>() {
        override fun areItemsTheSame(oldItem: CurrencyType, newItem: CurrencyType) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CurrencyType, newItem: CurrencyType) =
            oldItem.typeId == newItem.typeId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductFilterItemViewHolder {
        return ProductFilterItemViewHolder(
            FilterItemBinding.inflate(
                parent.layoutInflator,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductFilterItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductFilterItemViewHolder(private val binder: FilterItemBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bind(item: CurrencyType) = with(binder) {
            root.setOnClickListener { onFilterClicked(item, absoluteAdapterPosition) }
            binder.filterTv.text = item.name
        }
    }

}