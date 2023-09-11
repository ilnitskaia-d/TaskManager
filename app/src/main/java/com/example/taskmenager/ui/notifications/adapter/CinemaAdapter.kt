package com.example.taskmenager.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmenager.databinding.ItemTaskBinding
import com.example.taskmenager.model.Cinema

class CinemaAdapter():
    Adapter<CinemaAdapter.CinemaViewHolder>() {

    private val list: ArrayList<Cinema> = arrayListOf()

    fun addCinema(cinema: Cinema){
        list.add(0, cinema)
        notifyItemChanged(0)
    }

    fun addCinemas(cinema: List<Cinema>)
    {
        list.clear()
        list.addAll(cinema)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        return CinemaViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CinemaViewHolder(private val binding: ItemTaskBinding): ViewHolder(binding.root) {
        fun bind(cinema: Cinema) = with(binding) {
            tvTitle.text = cinema.name
            tvDesc.text = cinema.director
        }
    }
}