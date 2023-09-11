package com.example.taskmenager.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.taskmenager.R
import com.example.taskmenager.data.local.Pref
import com.example.taskmenager.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        Glide.with(binding.imgProfile).load(uri).into(binding.imgProfile)
        pref.safeImg(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnExit.setOnClickListener {
            findNavController().navigate(R.id.phoneFragment)
            auth.signOut()
        }

        Glide.with(binding.imgProfile).load(pref.getImg()).into(binding.imgProfile)
        binding.imgProfile.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.etNameProfile.setText(pref.getName())
        binding.etNameProfile.addTextChangedListener {
            pref.saveName(binding.etNameProfile.text.toString())
        }

    }
}