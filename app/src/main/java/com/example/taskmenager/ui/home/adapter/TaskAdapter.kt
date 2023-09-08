package com.example.taskmenager.ui.home.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmenager.databinding.ItemTaskBinding
import com.example.taskmenager.model.Task

class TaskAdapter(private val onLongClick: (Task) -> Unit, private val onClick: (Task) -> Unit, private val onSuccess: (Task) -> Unit):
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
        holder.bind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding): ViewHolder(binding.root) {
        fun bind(task : Task) = with(binding) {
            tvTitle.text = task.title
            tvDesc.text = task.description

            checkbox.isChecked = task.isSuccess
            if(task.isSuccess) {
                tvTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvDesc.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                tvTitle.paintFlags = 0
                tvDesc.paintFlags = 0
            }
            checkbox.setOnCheckedChangeListener { _, isSuccess ->
                onSuccess(task.copy(isSuccess = isSuccess))
            }

            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
            itemView.setOnClickListener {
                onClick(task)
            }
        }
    }
}