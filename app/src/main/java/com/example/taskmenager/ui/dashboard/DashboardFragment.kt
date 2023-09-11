package com.example.taskmenager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskmenager.databinding.FragmentDashboardBinding
import com.example.taskmenager.model.Cinema
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val data = Cinema(
                name = binding.etName.text.toString(),
                director = binding.etDir.text.toString()
            )
            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(context, "Сохранено!", Toast.LENGTH_LONG).show()
                    binding.etName.text.clear()
                    binding.etDir.text.clear()
                }.addOnFailureListener {
                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}