package com.yandey.rxjava3_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yandey.rxjava3_android.data.local.entity.StudentEntity
import com.yandey.rxjava3_android.databinding.ItemListStudentBinding

class StudentAdapter(
    private val onEditListener: (StudentEntity) -> Unit
): ListAdapter<StudentEntity, StudentAdapter.StudentViewHolder>(DIFF_CALLBACK) {

    inner class StudentViewHolder(
        private val binding: ItemListStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(studentEntity: StudentEntity) = with(binding) {
            tvId.text = studentEntity.id
            tvName.text = studentEntity.name
            tvAge.text = studentEntity.age.toString()
            tvSubject.text = studentEntity.subject

            ibEdit.setOnClickListener {
                onEditListener(studentEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemListStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val students = getItem(position)
        holder.bind(students)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StudentEntity>() {
            override fun areItemsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StudentEntity, newItem: StudentEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}