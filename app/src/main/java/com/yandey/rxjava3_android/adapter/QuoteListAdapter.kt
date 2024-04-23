package com.yandey.rxjava3_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem
import com.yandey.rxjava3_android.databinding.ItemListQuoteBinding

class QuoteListAdapter : ListAdapter<QuoteResponseItem, QuoteListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemListQuoteBinding) : ViewHolder(binding.root) {
        fun bind(data: QuoteResponseItem) {
            binding.tvItemQuote.text = data.en
            binding.tvItemAuthor.text = data.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<QuoteResponseItem>() {
            override fun areItemsTheSame(oldItem: QuoteResponseItem, newItem: QuoteResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: QuoteResponseItem, newItem: QuoteResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}