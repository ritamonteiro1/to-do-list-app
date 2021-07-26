package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.Task


class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskListViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.itemTaskTitleTextView.text = task.title
            binding.itemTaskHourTextView.text = "${task.date} ${task.hour}"
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}