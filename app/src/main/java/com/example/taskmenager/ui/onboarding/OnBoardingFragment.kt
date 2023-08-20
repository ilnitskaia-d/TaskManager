package com.example.taskmenager.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmenager.databinding.FragmentOnboardingBinding
import com.example.taskmenager.ui.onboarding.Adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private  val adapter = OnBoardingAdapter(this::onClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        binding.indicators.attachTo(binding.viewPager)

        binding.btnSkip.setOnClickListener {
            onClick()
        }
    }

    private fun onClick() {
        findNavController().navigateUp()
    }
}