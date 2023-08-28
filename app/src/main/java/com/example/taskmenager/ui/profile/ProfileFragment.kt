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
import com.example.taskmenager.data.local.Pref
import com.example.taskmenager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
//    private val gallery = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI)

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        binding.imgProfile.setImageURI(uri)
        pref.safeImg(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etNameProfile.setText(pref.getName())
        binding.etNameProfile.addTextChangedListener {
            pref.saveName(binding.etNameProfile.text.toString())
        }
        val imgUri: Uri? = pref.getImg()
        if(imgUri != null) {
            Log.d("debug", "got here!")
//            context?.grantUriPermission("profile", imgUri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            binding.imgProfile.setImageURI(imgUri)
        }

        binding.imgProfile.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    companion object {
        const val pickImage = 100
    }
}