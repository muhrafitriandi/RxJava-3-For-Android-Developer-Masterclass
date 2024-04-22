package com.yandey.rxjava3_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponseItem
import com.yandey.rxjava3_android.databinding.ItemListTaskBinding

class TaskAdapter(
    private val onEditListener: (TaskResponseItem) -> Unit,
) : ListAdapter<TaskResponseItem, TaskAdapter.TaskViewHolder>(DIFF_CALLBACK) {

    inner class TaskViewHolder(
        private val binding: ItemListTaskBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: TaskResponseItem) = with(binding) {
            tvId.text = item.id.toString()
            tvTitle.text = item.title
            tvBody.text = item.body
            tvNote.text = item.note
            tvStatus.text = item.status

            ibEdit.setOnClickListener { onEditListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemListTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val tasks = getItem(position)
        holder.bind(tasks)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TaskResponseItem>() {
            override fun areItemsTheSame(oldItem: TaskResponseItem, newItem: TaskResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TaskResponseItem, newItem: TaskResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}