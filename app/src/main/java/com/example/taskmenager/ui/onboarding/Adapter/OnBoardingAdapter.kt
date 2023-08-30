package com.example.taskmenager.ui.onboarding.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmenager.R
import com.example.taskmenager.databinding.ItemOnboardingBinding
import com.example.taskmenager.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit): Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf(
        OnBoarding (title = "test1", description = "desc", anim = R.raw.animation_1),
        OnBoarding (title = "test2", description = "desc", anim = R.raw.animation_2),
        OnBoarding (title = "test3", description = "desc", anim = R.raw.animation_3)
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) : ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
            binding.tvTitle.text = onBoarding.title
            binding.tvDesc.text = onBoarding.description

            binding.btnStart.isVisible = adapterPosition == list.lastIndex
            binding.btnStart.setOnClickListener { onClick() }

            binding.tvSkip.isVisible = adapterPosition != list.lastIndex
            binding.tvSkip.setOnClickListener { onClick() }

            onBoarding.anim?.let { binding.animBoard.setAnimation(it) }
            binding.animBoard.playAnimation()
        }
    }
}
