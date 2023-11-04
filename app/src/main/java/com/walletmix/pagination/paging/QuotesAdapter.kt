package com.walletmix.pagination.paging

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.walletmix.pagination.databinding.ItemQuoteBinding
import com.walletmix.pagination.models.ResponseQuotes

class QuotesAdapter :
    PagingDataAdapter<ResponseQuotes.Result, QuotesAdapter.QuotesViewHolder>(Comparator) {


    inner class QuotesViewHolder(val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.tvQuotes.text = "${it.content} \nby : ${it.author}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(
            ItemQuoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val Comparator = object : DiffUtil.ItemCallback<ResponseQuotes.Result>() {
            override fun areItemsTheSame(
                oldItem: ResponseQuotes.Result,
                newItem: ResponseQuotes.Result
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ResponseQuotes.Result,
                newItem: ResponseQuotes.Result
            ): Boolean = oldItem == newItem

        }
    }
}