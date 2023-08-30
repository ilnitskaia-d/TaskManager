package com.example.taskmenager.ui.home.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmenager.databinding.ItemTaskBinding
import com.example.taskmenager.model.Task

class TaskAdapter(private val onLongClick: (Task) -> Unit, private val onClick: (Task) -> Unit):
    Adapter<TaskAdapter.TaskViewHolder>() {

    private val list: ArrayList<Task> = arrayListOf()

    fun addTask(task: Task){
        list.add(0, task)
        notifyItemChanged(0)
    }

    fun addTasks(tasks: List<Task>)
    {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding): ViewHolder(binding.root) {
        fun bind(task : Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.description
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
        }
    }
}