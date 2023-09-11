package com.example.taskmenager.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taskmenager.R
import com.example.taskmenager.databinding.FragmentAcceptBinding
import com.example.taskmenager.ui.auth.phone.PhoneFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class AcceptFragment : Fragment() {
    private lateinit var binding: FragmentAcceptBinding

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId: String = arguments?.getString(PhoneFragment.VER_KEY).toString()

        binding.btnAccept.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(verId, binding.etCode.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnSuccessListener {
            findNavController().navigate(R.id.navigation_home)
            Toast.makeText(context, "Авторизация прошла успешно!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}